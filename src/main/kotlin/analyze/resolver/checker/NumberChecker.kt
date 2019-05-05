package analyze.resolver.checker

import data.document.ScannedWord
import data.document.TokenType

object NumberChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        if (word.content.length < 3) return null

        word.content.forEach { element ->
            if (!element.isDigit()) return null
        }

        return TokenType.NUMBER
    }
}