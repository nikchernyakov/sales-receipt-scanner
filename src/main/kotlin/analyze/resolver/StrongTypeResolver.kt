package analyze.resolver

import data.document.ScannedWord
import data.document.TokenType
import utils.*

object StrongTypeResolver : TypeResolver<TokenType> {
    override fun resolveWord(word: ScannedWord): TokenType {
        return when (true) {
            checkPriceType(word.content) -> TokenType.PRICE
            checkCountType(word.content) -> TokenType.COUNT
            checkKeyType(word.content) -> TokenType.KEY
            checkWordType(word.content) -> TokenType.WORD
            else -> TokenType.TRASH
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

    fun checkKeyType(text: String): Boolean {
        if (text.last() == ':') return true

        return listOf("Стоимость", "Цена", "Скидка")
                .any { text.contains(it, ignoreCase = true) }
    }

    fun checkWordType(text: String): Boolean {
        return text.length < 18 && isWord(text)
    }

    fun checkPercentType(text: String): Boolean {
        return text.first().isDigit()
                && hasNumber(text) && compareSymbolWithList(text.last(), PERCENT_CANDIDATE)
    }
}