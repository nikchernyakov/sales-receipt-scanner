package parser

import data.document.ScannedLine
import data.document.ScannedWord

class TextParser {
    fun parseLine(line: String): ScannedLine {
        return ScannedLine("line_1",
                line.split(" ").asSequence().mapIndexed { index, s ->
                    ScannedWord("word_$index", s)
                }.toMutableList())
    }
}