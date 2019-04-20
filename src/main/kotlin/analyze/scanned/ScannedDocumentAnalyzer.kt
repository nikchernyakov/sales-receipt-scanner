package analyze.scanned

import data.document.*

abstract class ScannedDocumentAnalyzer<T : Token> {

    fun analyzeDocument(document: ScannedDocument): AnalyzedDocument<T> {
        return AnalyzedDocument(document.content.map { analyzeLine(it) })
    }

    fun analyzeLine(line: ScannedLine): AnalyzedLine<T> {
        val tokens = ArrayList<T>()
        analyzeWords(line.content.listIterator(), tokens)
        return AnalyzedLine(tokens)
    }

    abstract fun analyzeWords(listIterator: MutableListIterator<ScannedWord>, tokens: ArrayList<T>)
}