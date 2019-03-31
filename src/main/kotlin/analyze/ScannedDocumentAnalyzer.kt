package analyze

import data.document.*

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
                currentToken = Token(wordGroupType)
            }
            currentToken!!.content += (if (currentToken!!.content.isEmpty()) "" else " ") + it.content
        }
        if (currentToken != null) tokens.add(currentToken!!)
        return AnalyzedLine(tokens)
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
}