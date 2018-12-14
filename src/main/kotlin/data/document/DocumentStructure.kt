package data.document

/* Element */
interface Element {
    val id: String
    val content: ElementContent
}

interface ElementContent

/* Line */
class Line(override val id: String, override val content: LineContent) : Element

class LineContent(val words: Array<Word>) : ElementContent

/* Word */
class Word(override val id: String, override val content: WordContent) : Element

class WordContent(val text: String) : ElementContent