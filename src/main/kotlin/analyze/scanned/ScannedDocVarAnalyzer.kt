package analyze.scanned

import analyze.resolver.SoftTypeResolver
import analyze.resolver.StrongTypeResolver
import data.document.*

class ScannedDocVarAnalyzer : ScannedDocumentAnalyzer<VarToken>() {

    override fun analyzeWords(listIterator: MutableListIterator<ScannedWord>, tokens: ArrayList<VarToken>) {
        var currentToken: VarToken
        while (listIterator.hasNext()) {
            val currentWord = listIterator.next()
            val wordGroupType = StrongTypeResolver.resolveWord(currentWord, listIterator)
            val candidates = SoftTypeResolver.resolveWord(currentWord, listIterator)
            currentToken = VarToken(wordGroupType, currentWord.tab, candidates)
            tokens.add(currentToken)
        }
    }
}