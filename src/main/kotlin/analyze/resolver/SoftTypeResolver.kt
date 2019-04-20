package analyze.resolver

import analyze.resolver.checker.PriceChecker
import analyze.resolver.checker.TypeChecker
import data.document.ScannedWord
import data.document.TokenType

object SoftTypeResolver : TypeResolver<List<TokenType>> {

    private val checkers = listOf<TypeChecker>(
            PriceChecker
    )

    override fun resolveWord(word: ScannedWord): List<TokenType> {
        return checkers.mapNotNull { it.check(word) }
    }
}