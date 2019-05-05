package analyze.pattern

import data.document.TokenType

val ITEM_PATTERNS_TEMPLATES = listOf(
        // 1 line
        listOf(
                listOf(TokenType.NAME, TokenType.COUNT, TokenType.ADDITIONAL_PRICE, TokenType.TOTAL_PRICE)
        ),
        // 2 lines
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.ADDITIONAL_PRICE, TokenType.COUNT, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NUMBER, TokenType.NAME),
                listOf(TokenType.ADDITIONAL_PRICE, TokenType.COUNT, TokenType.TOTAL_PRICE)
        ),

        // 3 lines
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.KEY, TokenType.NUMBER),
                listOf(TokenType.KEY, TokenType.TOTAL_PRICE)
        )
)

//val ITEM_PATTERNS = ITEM_PATTERNS_TEMPLATES.map { ItemPatternFactory.buildItemPattern(it) }
