package analyze.resolver.checker

import data.document.ScannedWord
import data.document.TokenType

object PriceChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        if (word.content.length <= 3) return null

        var hasDot = false
        word.content.forEachIndexed { _, element ->
            if (element == '.') {
                // Check if dot already was
                if (hasDot) return null
                hasDot = true
            } else {
                if (!element.isDigit()) return null
            }
        }

        val hasEquals = word.neighbor.hasPrevious() && word.neighbor.previous!!.content.last() == '='
        return if (hasDot || hasEquals) TokenType.PRICE else null
    }
}