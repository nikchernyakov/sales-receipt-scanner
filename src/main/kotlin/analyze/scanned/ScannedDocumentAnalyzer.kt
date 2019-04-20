package analyze.scanned

import data.document.*

abstract class ScannedDocumentAnalyzer<T : Token> {

    fun analyzeDocument(document: ScannedDocument): AnalyzedDocument<T> {
        return AnalyzedDocument(document.content.map { analyzeLine(it) })
    }

    private fun analyzeLine(line: ScannedLine): AnalyzedLine<T> {
        return AnalyzedLine(analyzeWords(line))
    }

    protected abstract fun analyzeWords(line: ScannedLine): List<AnalyzedElement<T>>

}