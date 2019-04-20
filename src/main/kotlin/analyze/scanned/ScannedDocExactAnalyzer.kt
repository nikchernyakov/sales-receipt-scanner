package analyze.scanned

import analyze.resolver.StrongTypeResolver
import data.document.*

class ScannedDocExactAnalyzer : ScannedDocumentAnalyzer<Token>() {

    override fun analyzeWords(lineIterator: MutableListIterator<ScannedWord>, tokens: ArrayList<Token>) {
        var currentToken: Token? = null
        while (lineIterator.hasNext()) {
            val currentWord = lineIterator.next()
            val wordGroupType = StrongTypeResolver.resolveWord(currentWord, lineIterator)
            if (currentToken == null || !isWordBelongToToken(wordGroupType, currentToken)) {
                if (currentToken != null) tokens.add(currentToken)
                currentToken = Token(wordGroupType, currentWord.tab)
            }
            addWordToToken(currentToken, currentWord)
        }
        if (currentToken != null) tokens.add(currentToken)
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