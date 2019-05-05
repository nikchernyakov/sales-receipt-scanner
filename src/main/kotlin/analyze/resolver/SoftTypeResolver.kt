package analyze.resolver

import analyze.resolver.checker.*
import data.document.ScannedWord
import data.document.TokenType

object SoftTypeResolver : TypeResolver<List<TokenType>> {

    private val checkers = listOf(
            PriceChecker,
            CountChecker,
            KeyChecker,
            WordChecker,
            NumberChecker
            // TODO
    )

    override fun resolveWord(word: ScannedWord): List<TokenType> {
        val result = checkers.mapNotNull { it.check(word) }
        return if (result.isEmpty()) listOf(TokenType.TRASH) else result
    }
}