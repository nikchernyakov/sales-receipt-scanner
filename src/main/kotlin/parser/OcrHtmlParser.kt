package parser

import data.document.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import utils.findDocumentName
import java.util.stream.Collectors

object OcrHtmlParser {
    fun parseOcrHtml(html: String?): ScannedDocument {
        val htmlDocument = Jsoup.parse(html)
        return parseHtmlDocument(htmlDocument)
    }

    private fun parseHtmlDocument(htmlDocument: Document): ScannedDocument {
        val documentTitle = htmlDocument.getElementsByClass("ocr_page").first().attr("title")
        val documentName = findDocumentName(documentTitle)
        val scannedLines = htmlDocument.getElementsByClass("ocr_line")
                .stream()
                .map {
                    val words = it.getElementsByClass("ocrx_word")
                            .stream()
                            .map { ScannedWord(it.id(), WordContent(it.html())) }
                            .collect(Collectors.toList())
                    ScannedLine(it.id(), LineContent(words))
                }
                .collect(Collectors.toList())
        return ScannedDocument(documentName ?: "document", DocumentContent(scannedLines))
    }
}