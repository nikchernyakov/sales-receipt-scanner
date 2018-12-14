package parser

import data.document.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.stream.Collectors

object OcrHtmlParser {
    fun parseOcrHtml(html: String?): ScannedDocument {
        val htmlDocument = Jsoup.parse(html)
        return parseHtmlDocument(htmlDocument)
    }

    private fun parseHtmlDocument(htmlDocument: Document): ScannedDocument {
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
        return ScannedDocument("document", DocumentContent(scannedLines))
    }
}