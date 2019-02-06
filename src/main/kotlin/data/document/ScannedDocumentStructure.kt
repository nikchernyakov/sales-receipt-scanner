package data.document

import java.lang.StringBuilder

/* ScannedElement */
interface ScannedElement {
    val id: String
    val content: ElementContent
}

interface ElementContent

/* ScannedDocument */
class ScannedDocument(override val id: String, override val content: DocumentContent) : ScannedElement {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("ScannedDocument(id='$id'):\n")
        content.lines.forEach { stringBuilder.append("  ").append(it.toString()).append("\n")}

        return stringBuilder.toString()
    }

    fun toDocumentString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("ScannedDocument(id='$id'):\n")
        content.lines.forEach { stringBuilder.append("  ").append(it.toDocumentString()).append("\n")}

        return stringBuilder.toString()
    }
}

class DocumentContent(val lines: MutableList<ScannedLine>) : ElementContent

/* ScannedLine */
class ScannedLine(override val id: String, override val content: LineContent) : ScannedElement {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Line(id='$id'):\n")
        content.words.forEach { stringBuilder.append("    ").append(it.toString()).append("\n") }
        return stringBuilder.toString()
    }

    fun toDocumentString(): String {
        val stringBuilder = StringBuilder()
        content.words.forEach { stringBuilder.append("  ").append(it.toDocumentString())}
        return stringBuilder.toString()
    }
}

class LineContent(val words: MutableList<ScannedWord>) : ElementContent

/* ScannedWord */
class ScannedWord(override val id: String, override val content: WordContent) : ScannedElement {
    override fun toString(): String {
        return "Word(id='$id'): ${content.text}"
    }

    fun toDocumentString(): String {
        return content.text
    }
}

class WordContent(val text: String) : ElementContent