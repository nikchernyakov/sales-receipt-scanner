package analyze.pattern

import data.document.*
import utils.CoordinateUtils

object ItemPatternFactory {

    fun buildItemPattern(pattern: List<List<TokenType>>): ItemPattern {
        val linesPattern = ArrayList<ItemLinePattern>(pattern.size)
        var firstPriceLineIndex = -1

        pattern.forEachIndexed { lineIndex, linePattern ->
            val elementsPattern = ArrayList<ItemElementPattern>(linePattern.size)
            linePattern.forEach { tokenType ->
                // Search for first price in pattern
                if (firstPriceLineIndex == -1 &&
                        (tokenType == TokenType.TOTAL_PRICE || tokenType == TokenType.ADDITIONAL_PRICE))
                    firstPriceLineIndex = lineIndex

                elementsPattern.add(ItemElementPattern(tokenType))
            }
            linesPattern.add(ItemLinePattern(elementsPattern))
        }

        // Fill neighbors
        linesPattern.forEach {
            it.elementsPattern.forEachIndexed { index, element ->
                CoordinateUtils.fillNeighbors(index, element, it.elementsPattern)
            }
        }

        return ItemPattern(firstPriceLineIndex, linesPattern)
    }

    fun initItemPatterns(): List<ItemPattern> {
        return ITEM_PATTERNS_TEMPLATES.map { buildItemPattern(it) }
    }
}