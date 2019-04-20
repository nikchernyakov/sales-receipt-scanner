package analyze.resolver

import data.document.ScannedWord
import data.document.TokenType

object SoftTypeResolver : TypeResolver<List<TokenType>> {
    override fun resolveWord(word: ScannedWord, line: Iterator<ScannedWord>): List<TokenType> {
        return emptyList()
    }
}