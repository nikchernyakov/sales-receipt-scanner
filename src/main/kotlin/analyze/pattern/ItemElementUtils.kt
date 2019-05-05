package analyze.pattern

import data.document.AnalyzedElement
import data.document.PurchaseItem
import data.document.TokenType
import data.document.VarToken

interface ItemElementResolver {
    fun resolve(item: PurchaseItem, element: AnalyzedElement<VarToken>)
}

abstract class DefaultResolver: ItemElementResolver {

    override fun resolve(item: PurchaseItem, element: AnalyzedElement<VarToken>) {
        applyToItem(item, element.token.content)
    }

    protected abstract fun applyToItem(item: PurchaseItem, content: String)
}

object NameResolver: DefaultResolver() {

    override fun resolve(item: PurchaseItem, element: AnalyzedElement<VarToken>) {
        var currentElement = element
        // Get full name
        val contentBuilder: StringBuilder = StringBuilder()
        while (currentElement.token.candidates.contains(TokenType.WORD)) {
            contentBuilder.append(currentElement.token.content).append(" ")
            if (currentElement.neighbor.hasNext()) {
                currentElement = currentElement.neighbor.next!!
            } else {
                break
            }
        }
        applyToItem(item, contentBuilder.toString().trim())
    }

    override fun applyToItem(item: PurchaseItem, content: String) {
        item.itemName = content
    }
}

object TotalPriceResolver: DefaultResolver() {
    override fun applyToItem(item: PurchaseItem, content: String) {
        item.price = content.toFloatOrNull() ?: 0f
    }
}

object CountResolver: DefaultResolver() {
    override fun applyToItem(item: PurchaseItem, content: String) {
        item.itemCount = content.toIntOrNull() ?: 1
    }
}

object ItemElementUtils {

    private val resolverMap = mapOf(
        Pair(TokenType.TOTAL_PRICE, TotalPriceResolver),
        Pair(TokenType.NAME, NameResolver),
        Pair(TokenType.COUNT, CountResolver)
    )

    fun resolveElement(patternTokenType: TokenType, item: PurchaseItem, element: AnalyzedElement<VarToken>) {
        resolverMap[patternTokenType]?.resolve(item, element)
    }

    fun checkElementToken(patternTokenType: TokenType, token: VarToken): Boolean {
        return if (patternTokenType == TokenType.TOTAL_PRICE || patternTokenType == TokenType.ADDITIONAL_PRICE) {
            checkTokenType(TokenType.PRICE, token)
        } else if (patternTokenType == TokenType.NAME) {
            checkTokenType(TokenType.WORD, token)
        } else {
            checkTokenType(patternTokenType, token)
        }
    }

    private fun checkTokenType(tokenToCheck: TokenType, varToken: VarToken): Boolean {
        return varToken.candidates.any { it == tokenToCheck }
    }
}