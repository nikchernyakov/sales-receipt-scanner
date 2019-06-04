package analyze.resolver.checker

import data.document.ScannedWord
import data.document.TokenType

object NumberChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        word.content.forEachIndexed { index, element ->
            if (!element.isDigit() && !(index == word.content.length - 1 && element == '.')) return null
        }

        return TokenType.NUMBER
    }
}