package utils

import data.document.ScannedLine
import java.lang.StringBuilder

fun findItemPriceInEndOfLine(line: ScannedLine): Float? {
    var lastWord = StringBuilder(line.content.last().content)
    return lastWord.trim().toString().toFloat()
}

fun findDocumentName(documentTitle: String): String? {
    val imagePathStartIndex = documentTitle.indexOf('"')
    if (imagePathStartIndex == -1) return null

    val imagePathEndIndex = documentTitle.indexOf('"', imagePathStartIndex + 1)
    if (imagePathEndIndex == -1) return null

    return documentTitle.substring(imagePathStartIndex + 1, imagePathEndIndex)
}