package analyze.scanned

import analyze.resolver.StrongTypeResolver
import data.document.*
import utils.CoordinateUtils

class ScannedDocExactAnalyzer : ScannedDocumentAnalyzer<Token>() {

    override fun analyzeWords(line: ScannedLine): List<AnalyzedElement<Token>> {
        val tokens = ArrayList<Token>()
        var currentToken: Token? = null
        line.content.forEach {
            // Resolve token
            val wordGroupType = StrongTypeResolver.resolveWord(it)

            // Check if can't add to previous token
            if (currentToken == null || !isWordBelongToToken(wordGroupType, currentToken!!)) {
                // Create next token
                if (currentToken != null) tokens.add(currentToken!!)
                currentToken = Token(wordGroupType, it.tab)
            }
            addWordToToken(currentToken!!, it)
        }
        // Add last token
        if (currentToken != null) tokens.add(currentToken!!)

        // Create elements
        val elements = tokens.map {
            AnalyzedElement(it)
        }
        elements.forEachIndexed { index, element ->
            CoordinateUtils.fillNeighbors(index, element, elements)
        }
        return elements
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
}