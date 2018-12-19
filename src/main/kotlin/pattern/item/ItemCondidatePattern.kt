package pattern.item

import data.document.PurchaseItem
import data.document.ScannedLine

interface ItemCondidatePattern {
    fun getItem(lines: List<ScannedLine>): PurchaseItem?
    fun getLineCount(): Int
}