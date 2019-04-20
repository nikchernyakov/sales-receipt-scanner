package analyze

import data.document.*
import utils.isNumber

class ScannedDocumentAnalyzer {

    fun analyzeDocument(document: ScannedDocument): List<AnalyzedLine> {
        return document.content.map { analyzeLine(it) }
    }

    fun analyzeLine(line: ScannedLine): AnalyzedLine {
        val tokens = ArrayList<Token>()
        var currentToken: Token? = null
        line.content.forEach {
            val wordGroupType = resolveWordTokenType(it)
            if (currentToken == null || !isWordBelongToToken(wordGroupType, currentToken!!)) {
                if (currentToken != null) tokens.add(currentToken!!)
                currentToken = Token(wordGroupType, it.tab)
            }
            addWordToToken(currentToken!!, it)
        }
        if (currentToken != null) tokens.add(currentToken!!)
        return AnalyzedLine(tokens)
    }

    private fun addWordToToken(token: Token, word: ScannedWord) {
        token.content += (if (token.content.isEmpty()) "" else " ") + word.content
        // Get full size of tab from token.tab.index to word.tab.endIndex
        token.tab.count = word.tab.endIndex - token.tab.index
    }

    private fun isWordBelongToToken(wordTokenType: TokenType, token: Token): Boolean {
        return when (token.type) {
            TokenType.KEY -> {
                wordTokenType == TokenType.KEY
            }
            else -> {
                false
            }
        }
    }

    fun resolveWordTokenType(word: ScannedWord): TokenType {
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