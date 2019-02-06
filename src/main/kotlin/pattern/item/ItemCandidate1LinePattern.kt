package pattern.item

import data.document.PurchaseItem
import data.document.ScannedLine
import utils.findItemPriceInEndOfLine

class ItemCandidate1LinePattern() : ItemCandidatePattern {
    override fun getLineCount(): Int {
        return 1
    }

    override fun getItem(lines: List<ScannedLine>): PurchaseItem? {
        val line = lines.first()

        val price = findItemPriceInEndOfLine(line)

        return null
    }

}