package analyze.pattern

import data.document.TokenType

val ITEM_PATTERNS_TEMPLATES = listOf(
        // 1 line
        listOf(
                listOf(TokenType.NAME, TokenType.COUNT, TokenType.ADDITIONAL_PRICE, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME, TokenType.PERCENT, TokenType.COUNT, TokenType.ADDITIONAL_PRICE, TokenType.TOTAL_PRICE)
        ),

        // 2 lines
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.COUNT, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.ADDITIONAL_PRICE, TokenType.COUNT, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.ADDITIONAL_PRICE, TokenType.ADDITIONAL_PRICE, TokenType.COUNT, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.COUNT, TokenType.ADDITIONAL_PRICE, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.ADDITIONAL_PRICE, TokenType.COUNT, TokenType.TOTAL_PRICE, TokenType.PERCENT)
        ),
        listOf(
                listOf(TokenType.NUMBER, TokenType.NAME),
                listOf(TokenType.ADDITIONAL_PRICE, TokenType.COUNT, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME, TokenType.COUNT, TokenType.ADDITIONAL_PRICE),
                listOf(TokenType.PERCENT, TokenType.TOTAL_PRICE)
        ),

        // 3 lines
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.KEY, TokenType.NUMBER),
                listOf(TokenType.KEY, TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME, TokenType.COUNT, TokenType.ADDITIONAL_PRICE),
                listOf(TokenType.TOTAL_PRICE),
                listOf(TokenType.PERCENT, TokenType.ADDITIONAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NAME),
                listOf(TokenType.COUNT, TokenType.ADDITIONAL_PRICE),
                listOf(TokenType.TOTAL_PRICE)
        ),
        listOf(
                listOf(TokenType.NUMBER, TokenType.NAME),
                listOf(TokenType.COUNT, TokenType.ADDITIONAL_PRICE),
                listOf(TokenType.TOTAL_PRICE)
        ),

        // 5 lines
        listOf(
                listOf(TokenType.WORD),
                listOf(TokenType.NUMBER, TokenType.NAME),
                listOf(TokenType.WORD),
                listOf(TokenType.COUNT, TokenType.ADDITIONAL_PRICE),
                listOf(TokenType.TOTAL_PRICE)
        )
)

//val ITEM_PATTERNS = ITEM_PATTERNS_TEMPLATES.map { ItemPatternFactory.buildItemPattern(it) }
