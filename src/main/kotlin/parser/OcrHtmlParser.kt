package parser

import data.document.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import utils.findBox
import utils.findDocumentName
import java.util.stream.Collectors

object OcrHtmlParser {

    private const val TAB_COUNT = 20

    private var currentTabSize: Int = 1

    fun parseOcrHtml(html: String?): ScannedDocument {
        val htmlDocument = Jsoup.parse(html)
        return parseHtmlDocument(htmlDocument)
    }

    private fun parseHtmlDocument(htmlDocument: Document): ScannedDocument {
        val documentTitle = getTitle(htmlDocument.getElementsByClass("ocr_page").first())
        val documentName = findDocumentName(documentTitle)
        val documentBox = findBox(documentTitle)
        currentTabSize = documentBox.width / TAB_COUNT

        val scannedLines = htmlDocument.getElementsByClass("ocr_line")
                .stream()
                .map {
                    parseHtmlLine(it, documentBox)
                }
                .collect(Collectors.toList())
        return ScannedDocument(documentName ?: "document", scannedLines, documentBox)
    }

    private fun getTab(wordBox: Box, documentBox: Box): Tab {
        return Tab((wordBox.startPoint.x - documentBox.startPoint.x) / currentTabSize,
                (wordBox.width / currentTabSize) + 1)
    }

    private fun getTitle(element: Element): String {
        return element.attr("title")
    }

    private fun parseHtmlLine(element: Element, documentBox: Box): ScannedLine {
        val words = element.getElementsByClass("ocrx_word")
                .stream()
                .map { parseHtmlWord(it, documentBox) }
                .collect(Collectors.toList())
        return ScannedLine(element.id(), words, parseBox(element))
    }

    private fun parseHtmlWord(element: Element, documentBox: Box): ScannedWord {
        val wordBox = parseBox(element)
        return ScannedWord(element.id(),
                if (element.children().size > 0) element.child(0).html() else element.html(),
                wordBox, getTab(wordBox, documentBox))
    }

    private fun parseBox(element: Element): Box {
        return findBox(getTitle(element))
    }
}