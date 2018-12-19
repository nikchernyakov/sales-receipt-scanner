package utils

import data.document.ScannedLine
import java.lang.StringBuilder

fun findItemPriceInEndOfLine(line: ScannedLine): Float? {
    var lastWord = StringBuilder(line.content.words.last().content.text)
    return lastWord.trim().toString().toFloat()
}