package analyze.resolver.checker

import analyze.resolver.StrongTypeResolver
import data.document.ScannedWord
import data.document.TokenType

object WordChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        return if (StrongTypeResolver.checkWordType(word.content)) TokenType.WORD else null
    }
}