package parser

import data.document.ScannedDocument
import data.document.ScannedLine
import data.document.ScannedWord

class TextParser {

    fun parseText(text: String): ScannedDocument {
        val lines = text
                .split("\n")
                .map { parseLine(it) }.toMutableList()
        return ScannedDocument("parsed_document", lines)
    }

    fun parseLine(line: String): ScannedLine {
        return ScannedLine("line_1",
                line.split(" ").asSequence().mapIndexed { index, s ->
                    ScannedWord("word_$index", s)
                }.toMutableList())
    }
}