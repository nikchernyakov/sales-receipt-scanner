package parser

import data.document.PurchaseInfo
import data.document.PurchaseItem
import data.document.ScannedDocument
import pattern.candidate.ItemListCandidate

object ReceiptScannedTextParser {
    fun parsePurchaseInfo(document: ScannedDocument): PurchaseInfo {
        // Get all candidates
        var itemListCandidate = findAllPurchaseInfoCandidates(document)

        // Choose the most probable candidate
        var resultItems: MutableList<PurchaseItem> = ArrayList()

        return PurchaseInfo(0f, resultItems)
    }

    fun findAllPurchaseInfoCandidates(document: ScannedDocument): List<ItemListCandidate> {
        return emptyList()
    }
}