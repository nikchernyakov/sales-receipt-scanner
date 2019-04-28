package analyze.resolver.checker

import analyze.resolver.StrongTypeResolver
import data.document.ScannedWord
import data.document.TokenType

object KeyChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        return if (StrongTypeResolver.checkKeyType(word.content)) TokenType.KEY else null
    }
}