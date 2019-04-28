package analyze.resolver

import analyze.resolver.checker.CountChecker
import analyze.resolver.checker.KeyChecker
import analyze.resolver.checker.PriceChecker
import analyze.resolver.checker.WordChecker
import data.document.ScannedWord
import data.document.TokenType

object SoftTypeResolver : TypeResolver<List<TokenType>> {

    private val checkers = listOf(
            PriceChecker,
            CountChecker,
            KeyChecker,
            WordChecker
            // TODO
    )

    override fun resolveWord(word: ScannedWord): List<TokenType> {
        val result = checkers.mapNotNull { it.check(word) }
        return if (result.isEmpty()) listOf(TokenType.TRASH) else result
    }
}