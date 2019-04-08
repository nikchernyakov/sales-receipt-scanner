package analyze

import data.document.*

class TokenAnalyzer {

    fun analyzeItems(document: AnalyzedDocument): List<PurchaseItem> {
        val lineIterator = document.lines.listIterator()
        val items = ArrayList<PurchaseItem>()
        var nextItem: PurchaseItem?

        do {
            nextItem = findNextItem(lineIterator)
            if (nextItem != null) {
                items.add(nextItem)
            }

        } while (nextItem != null)
        return items
    }

    private fun findNextItem(lineIterator: ListIterator<AnalyzedLine>): PurchaseItem? {
        val linesSelection = ArrayList<AnalyzedLine>()
        var currentLine: AnalyzedLine
        // Get next lines
        while (lineIterator.hasNext()) {
            currentLine = lineIterator.next()
            linesSelection.add(currentLine)

            // Find a price
            val priceToken = findPriceInLine(currentLine)
            if (priceToken != null) {
                val price = priceToken.content.toFloatOrNull() ?: continue

                // Find a purchase item if price is exist
                val purchaseItem = getPurchaseItem(price, linesSelection)
                if (purchaseItem != null) {
                    return purchaseItem
                }
            }
        }
        return null
    }

    private fun getPurchaseItem(price: Float, linesSelection: List<AnalyzedLine>): PurchaseItem? {
        val itemCount = 1
        var itemName: String? = null

        for (line in linesSelection) {
            for (token in line.tokens) {
                if (token.type == TokenType.KEY) {
                    itemName = token.content
                }
            }
        }

        return if (itemName != null) {
            PurchaseItem(itemName, itemCount, price)
        } else {
            null
        }
    }

    private fun findPriceInLine(line: AnalyzedLine): Token? {
        for (token in line.tokens) {
            if (token.type == TokenType.PRICE) {
                return token
            }
        }
        return null
    }
}