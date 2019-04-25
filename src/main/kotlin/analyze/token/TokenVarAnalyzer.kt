package analyze.token

import analyze.item.PurchaseItemBlock
import data.document.*

class TokenVarAnalyzer : TokenAnalyzer<VarToken> {

    // First tab index where price can be
    private val PRICE_TAB_INDEX = 16

    // To exclude
    private val MAX_LINES_DIFFERENCE = 3

    override fun analyzeItems(document: AnalyzedDocument<VarToken>): List<PurchaseItem> {
        val priceColumn = getPriceColumn(document.lines)

        val itemBlocks = getItemBlocks(priceColumn)

        TODO()
    }

    private fun getItemBlocks(priceColumn: List<Pair<Int, Int>>): List<PurchaseItemBlock> {
        val minDistance = 1
        TODO("not implemented")
    }

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