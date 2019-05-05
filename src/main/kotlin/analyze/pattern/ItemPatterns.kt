package analyze.pattern

import data.document.TokenType

val ITEM_PATTERNS_TEMPLATES = listOf(
        // 1 line
        // 2 lines
        listOf(
                listOf(TokenType.WORD),
                listOf(TokenType.PRICE, TokenType.COUNT, TokenType.PRICE)
        ),
        listOf(
                listOf(TokenType.NUMBER, TokenType.WORD),
                listOf(TokenType.PRICE, TokenType.COUNT, TokenType.PRICE)
        ),

        // 3 lines
        listOf(
                listOf(TokenType.WORD),
                listOf(TokenType.KEY, TokenType.NUMBER),
                listOf(TokenType.KEY, TokenType.PRICE)
        )
)

//val ITEM_PATTERNS = ITEM_PATTERNS_TEMPLATES.map { ItemPatternFactory.buildItemPattern(it) }
