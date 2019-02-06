package pattern.item

import data.document.PurchaseItem
import data.document.ScannedLine

interface ItemCandidatePattern {
    fun getItem(lines: List<ScannedLine>): PurchaseItem?
    fun getLineCount(): Int
}