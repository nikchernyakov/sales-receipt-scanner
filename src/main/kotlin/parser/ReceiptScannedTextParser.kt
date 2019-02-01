package parser

import data.document.PurchaseInfo
import data.document.PurchaseItem

object ReceiptScannedTextParser {
    fun parseTextPurchaseInfo(): PurchaseInfo {
        var items = ArrayList<PurchaseItem>()
        return PurchaseInfo(0f, items)
    }
}