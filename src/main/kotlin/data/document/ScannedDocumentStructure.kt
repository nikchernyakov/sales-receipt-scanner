package data.document

import java.lang.StringBuilder

/* ScannedElement */
interface ScannedElement<T> {
    val id: String
    val content: T
    val box: Box
}

/* ScannedDocument */
class ScannedDocument(override val id: String,
                      override val content: MutableList<ScannedLine>,
                      override val box: Box) : ScannedElement<MutableList<ScannedLine>> {

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("ScannedDocument(id='$id'):\n")
        content.forEach { stringBuilder.append("  ").append(it.toString()).append("\n")}

        return stringBuilder.toString()
    }

    fun toDocumentString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("ScannedDocument(id='$id'):\n")
        content.forEach { stringBuilder.append("  ").append(it.toDocumentString()).append("\n")}

        return stringBuilder.toString()
    }
}

/* ScannedLine */
class ScannedLine(override val id: String,
                  override val content: MutableList<ScannedWord>,
                  override val box: Box) : ScannedElement<MutableList<ScannedWord>> {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Line(id='$id'):\n")
        content.forEach { stringBuilder.append("    ").append(it.toString()).append("\n") }
        return stringBuilder.toString()
    }

    fun toDocumentString(): String {
        val stringBuilder = StringBuilder()
        content.forEach { stringBuilder.append("  ").append(it.toDocumentString())}
        return stringBuilder.toString()
    }
}

/* ScannedWord */
class ScannedWord(override val id: String,
                  override val content: String,
                  override val box: Box,
                  val tab: Tab) : ScannedElement<String>, InlineElement<ScannedWord> {
    override val neighbor = Neighbor<ScannedWord>()

    override fun toString(): String {
        return "Word('$id', $box, $tab): $content"
    }

    fun toDocumentString(): String {
        return content
    }
}