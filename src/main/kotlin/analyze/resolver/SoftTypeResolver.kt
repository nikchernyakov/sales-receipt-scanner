package analyze.resolver

import data.document.ScannedWord
import data.document.TokenType

object SoftTypeResolver : TypeResolver<List<TokenType>> {
    override fun resolveWord(word: ScannedWord, lineIterator: MutableListIterator<ScannedWord>): List<TokenType> {
        return emptyList()
    }
}