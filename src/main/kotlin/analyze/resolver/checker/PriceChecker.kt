package analyze.resolver.checker

import data.document.ScannedWord
import data.document.TokenType

object PriceChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        if (word.content.length <= 3) return null

        var hasDot = false
        var startPassed = false
        var digitPassed = false
        var endPassed = false
        word.content.forEachIndexed { _, element ->
            if (digitPassed && element == '.') {
                // Check if dot already was
                if (hasDot) return null
                hasDot = true
            } else {
                if (!element.isDigit()) {
                    if (!startPassed && !digitPassed) {
                        // If prefix is not digit
                        startPassed = true
                    } else if (!endPassed && digitPassed) {
                        // If suffix is not digit
                        endPassed = true
                    }
                } else {
                    if (endPassed) {
                        // After price come next digit
                        return null
                    }

                    digitPassed = true
                }
            }
        }

        val hasEquals = word.neighbor.hasPrevious() && word.neighbor.previous!!.content.last() == '='
                || word.content.first() == '='
        return if (hasDot || hasEquals) TokenType.PRICE else null
    }
}