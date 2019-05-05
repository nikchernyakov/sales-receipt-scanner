package analyze.token

import analyze.pattern.ItemPatternFactory
import analyze.pattern.ItemElementUtils
import data.document.*

class TokenVarAnalyzer : TokenAnalyzer<VarToken> {

    // First tab index where price can be
    private val PRICE_TAB_INDEX = 16

    // To exclude
    private val MAX_LINES_DIFFERENCE = 3

    private val ITEM_PRICE_PATTERNS = listOf(
            ItemPricePattern(listOf(true)),
            ItemPricePattern(listOf(false, true)),
            ItemPricePattern(listOf(false, true, true))
    )

    override fun analyzeItems(document: AnalyzedDocument<VarToken>): List<PurchaseItem> {
        val priceColumn = getPriceColumn(document.lines)
        //val priceDistances = getPriceDistances(priceColumn)

        val itemPatterns = ItemPatternFactory.initItemPatterns()
        findCandidates(document, priceColumn, itemPatterns)

        val resultItemPattern = chooseResultPattern(itemPatterns)
        if (resultItemPattern == null || resultItemPattern.candidateList.isEmpty())
            return emptyList()

        return resultItemPattern.candidateList
    }

    private fun chooseResultPattern(itemPatterns: List<ItemPattern>): ItemPattern? {
        return itemPatterns.maxBy { it.candidateList.size }
    }

    private fun findCandidates(document: AnalyzedDocument<VarToken>,
                               priceColumn: List<Pair<Int, Int>>,
                               itemPatterns: List<ItemPattern>) {
        priceColumn.forEach { (firstPriceLineInd, _) ->
            itemPatterns.forEach { itemPattern ->
                val resultItem = checkItemPattern(document, firstPriceLineInd, itemPattern)
                if (resultItem != null) {
                    itemPattern.candidateList.add(resultItem)
                }
            }
        }
    }

    private fun checkItemPattern(document: AnalyzedDocument<VarToken>,
                                 firstPriceLineIndex: Int,
                                 itemPattern: ItemPattern): PurchaseItem? {
        // Check bounds if it possible to use this pattern
        if (itemPattern.firstPriceLineIndex > firstPriceLineIndex
                || itemPattern.linesPattern.size - itemPattern.firstPriceLineIndex
                > document.lines.size - firstPriceLineIndex)
            return null

        val purchaseItem = PurchaseItem()

        /* Check pattern */
        // Move to first line in pattern
        val startLineInd = firstPriceLineIndex - itemPattern.firstPriceLineIndex
        var currentPatternLineInd = 0

        var currentPatternElement: ItemElementPattern
        var currentElement: AnalyzedElement<VarToken>

        while (currentPatternLineInd < itemPattern.linesPattern.size) {
            // Update line
            currentPatternElement = itemPattern.linesPattern[currentPatternLineInd].elementsPattern.first()
            currentElement = document.lines[startLineInd + currentPatternLineInd].elements.first()

            // Check line
            val checkResult = checkPatternLine(currentPatternElement, currentElement, purchaseItem)
            if (!checkResult) {
                // If line is not good for pattern
                return null
            }

            // Move to next line
            currentPatternLineInd++
        }

        return purchaseItem
    }

    private fun checkPatternLine(startPatternElement: ItemElementPattern,
                                 startElement: AnalyzedElement<VarToken>,
                                 purchaseItem: PurchaseItem): Boolean {
        var currentPatternElement = startPatternElement
        var currentElement = startElement
        while (currentPatternElement.neighbor.hasNext()) {
            while (!ItemElementUtils.checkElementToken(currentPatternElement.tokenType, currentElement.token)) {
                if (!currentElement.neighbor.hasNext()) {
                    return false
                }
                currentElement = currentElement.neighbor.next!!
            }
            // Fill PurchaseItem
            ItemElementUtils.resolveElement(currentPatternElement.tokenType, purchaseItem, currentElement)

            currentPatternElement = currentPatternElement.neighbor.next!!
        }
        return true
    }

    /*private fun getPriceDistances(priceColumn: List<Pair<Int, Int>>): Set<Int> {
        val distances = HashSet<Int>()
        priceColumn.forEachIndexed { index, pricePair ->
            if (index == 0) {
                return@forEachIndexed
            }

            distances.add(pricePair.first - priceColumn[index - 1].first)
        }
        return distances
    }

    private fun getItemCandidates(priceColumn: List<Pair<Int, Int>>,
                                  priceDistances: Set<Int>): List<ItemCandidate> {
        val itemCandidates = ArrayList<ItemCandidate>()

        priceColumn.forEachIndexed { index, pricePair ->
            itemCandidates.addAll(createItemCandidatesForLine(index, pricePair, priceColumn, priceDistances))
        }

        return itemCandidates
    }

    private fun createItemCandidatesForLine(index: Int, pricePair: Pair<Int, Int>,
                                            priceColumn: List<Pair<Int, Int>>,
                                            priceDistances: Set<Int>): List<ItemCandidate> {
        val itemCandidates = ArrayList<ItemCandidate>()

        ITEM_PRICE_PATTERNS.forEach {
            if (!it.pattern.first()) {

            } else {
                itemCandidates.add(ItemCandidate(pricePair.first, 1, it))
            }
        }
        return itemCandidates
    }*/

    /*
        List of line's indexes that contains Price and index of token
     */
    protected fun getPriceColumn(lines: List<AnalyzedLine<VarToken>>): List<Pair<Int, Int>> {
        val priceColumn = ArrayList<Pair<Int, Int>>()

        // Find last price tokens in lines
        lines.forEachIndexed { lineIndex, line ->
            // Get index of price token that further than PRICE_TAB_INDEX
            val tokenIndex = line.elements.indexOfLast { it.token.candidates.contains(TokenType.PRICE) }
            if (line.elements[tokenIndex].token.tab.index >= PRICE_TAB_INDEX) {
                priceColumn.add(Pair(lineIndex, tokenIndex))
            }
        }

        if (priceColumn.size == 0) {
            throw IllegalStateException("There is no tokens with price after tab=$PRICE_TAB_INDEX")
        }

        return priceColumn
    }
}