package analyze.scanned

import data.document.*

abstract class ScannedDocumentAnalyzer<T : Token> {

    fun analyzeDocument(document: ScannedDocument): AnalyzedDocument<T> {
        return AnalyzedDocument(document.content.map { analyzeLine(it) })
    }

    private fun analyzeLine(line: ScannedLine): AnalyzedLine<T> {
        val tokens = ArrayList<T>()
        analyzeWords(line.content.listIterator(), tokens)
        return AnalyzedLine(tokens)
    }

    protected abstract fun analyzeWords(lineIterator: MutableListIterator<ScannedWord>,
                                        tokens: ArrayList<T>)
}