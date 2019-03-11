package analyze

import data.document.*

class ScannedDocumentAnalyzer {

    fun analyzeDocument(document: ScannedDocument): List<AnalyzedBlock> {
        return document.content.map { analyzeLine(it) }
    }

    fun analyzeLine(line: ScannedLine): AnalyzedBlock {
        val groups = ArrayList<AnalyzedGroup>()
        var currentGroup: AnalyzedGroup? = null
        line.content.forEach {
            val wordGroupType = resolveWordGroupType(it)
            if (currentGroup == null || !isWordBelongToGroup(wordGroupType, currentGroup!!)) {
                if (currentGroup != null) groups.add(currentGroup!!)
                currentGroup = AnalyzedGroup(wordGroupType)
            }
            currentGroup!!.content += (if (currentGroup!!.content.isEmpty()) "" else " ") + it.content
        }
        if (currentGroup != null) groups.add(currentGroup!!)
        return AnalyzedBlock(groups)
    }

    private fun isWordBelongToGroup(wordGroupType: AnalyzedGroupType, group: AnalyzedGroup): Boolean {
        return when (group.groupType) {
            AnalyzedGroupType.KEY -> {
                wordGroupType == AnalyzedGroupType.KEY
            }
            AnalyzedGroupType.PRICE -> {
                false
            }
        }
    }

    fun resolveWordGroupType(word: ScannedWord): AnalyzedGroupType {
        return when (true) {
            checkPriceType(word.content) -> AnalyzedGroupType.PRICE
            else -> AnalyzedGroupType.KEY
        }
    }

    fun checkPriceType(text: String): Boolean {
        if (text.length <= 3) return false

        text.forEachIndexed { index, element ->
            if (index == text.length - 3) {
                if (element != '.') return false
            } else {
                if (!element.isDigit()) return false
            }
        }
        return true
    }
}