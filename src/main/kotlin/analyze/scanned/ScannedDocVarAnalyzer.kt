package analyze.scanned

import analyze.resolver.SoftTypeResolver
import analyze.resolver.StrongTypeResolver
import data.document.AnalyzedElement
import data.document.ScannedLine
import data.document.VarToken
import utils.CoordinateUtils

class ScannedDocVarAnalyzer : ScannedDocumentAnalyzer<VarToken>() {

    override fun analyzeWords(line: ScannedLine): List<AnalyzedElement<VarToken>> {
        val elements = createElements(line)
        line.content.forEachIndexed { index, word ->
            val currentElement = elements[index]
            CoordinateUtils.fillNeighbors(index, currentElement, elements)

            val wordGroupType = StrongTypeResolver.resolveWord(word)
            val candidates = SoftTypeResolver.resolveWord(word)
            currentElement.token = VarToken(wordGroupType, word.tab, candidates)
            currentElement.token.content = word.content
        }
        return elements
    }

    protected fun createElements(line: ScannedLine): List<AnalyzedElement<VarToken>> {
        return line.content.map { AnalyzedElement(VarToken()) }
    }
}