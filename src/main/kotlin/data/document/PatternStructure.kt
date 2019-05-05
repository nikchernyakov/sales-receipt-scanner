package data.document

class ItemPricePattern(val pattern: List<Boolean>) {
    override fun toString(): String {
        return pattern.toString()
    }
}

class ItemCandidate(val startLine: Int, val lineCount: Int, val pricePattern: ItemPricePattern) {

    val mark = Mark()

    override fun toString(): String {
        return "ItemCandidate(start=$startLine, count=$lineCount, pricePattern=$pricePattern)"
    }
}

class ItemPattern(val firstPriceLineIndex: Int,
                  val linesPattern: List<ItemLinePattern>) {
    val candidateList = ArrayList<PurchaseItem>()
}

class ItemLinePattern(val elementsPattern: List<ItemElementPattern>)

class ItemElementPattern(val tokenType: TokenType): InlineElement<ItemElementPattern> {
    override val neighbor: Neighbor<ItemElementPattern> = Neighbor()
}

class Mark {
    var itemsCount: Int = 0
}