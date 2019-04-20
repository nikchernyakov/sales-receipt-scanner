package analyze.scanned

import analyze.resolver.SoftTypeResolver
import analyze.resolver.StrongTypeResolver
import data.document.*

class ScannedDocVarAnalyzer : ScannedDocumentAnalyzer<VarToken>() {

    override fun analyzeWords(lineIterator: MutableListIterator<ScannedWord>, tokens: ArrayList<VarToken>) {
        var currentToken: VarToken
        while (lineIterator.hasNext()) {
            val currentWord = lineIterator.next()
            val wordGroupType = StrongTypeResolver.resolveWord(currentWord, lineIterator)
            val candidates = SoftTypeResolver.resolveWord(currentWord, lineIterator)
            currentToken = VarToken(wordGroupType, currentWord.tab, candidates)
            tokens.add(currentToken)
        }
    }
}