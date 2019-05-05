package analyze.resolver.checker

import analyze.resolver.StrongTypeResolver
import data.document.ScannedWord
import data.document.TokenType

object PercentChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        return if (StrongTypeResolver.checkPercentType(word.content)) TokenType.PERCENT else null
    }
}