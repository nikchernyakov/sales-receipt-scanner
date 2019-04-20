package analyze.resolver

import data.document.ScannedWord
import data.document.TokenType
import utils.isNumber

object StrongTypeResolver : TypeResolver<TokenType> {
    override fun resolveWord(word: ScannedWord, line: Iterator<ScannedWord>): TokenType {
        return when (true) {
            checkPriceType(word.content) -> TokenType.PRICE
            checkCountType(word.content) -> TokenType.COUNT
            else -> TokenType.KEY
        }
    }

    fun checkPriceType(text: String): Boolean {
        if (text.length <= 3) return false

        text.forEachIndexed { index, element ->
            if (index == text.length - 3) {
                if (element != '.') return false
            } else {
                if (!element.isDigit()) return false
            }
        }
        return true
    }

    fun checkCountType(text: String): Boolean {
        if (text.length < 3) {
            return isNumber(text)
        }

        text.forEachIndexed { index, element ->
            if (index == text.length - 4) {
                if (element != '.') return false
            } else {
                if (!element.isDigit()) return false
            }
        }

        return true
    }
}