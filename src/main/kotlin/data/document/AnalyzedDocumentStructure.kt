package data.document

class AnalyzedDocument(var blocks: List<AnalyzedBlock>)

class AnalyzedBlock(var groups: List<AnalyzedGroup>) {
    override fun toString(): String {
        return "AnalyzedBlock(groups=$groups)"
    }
}

class AnalyzedGroup(var groupType: AnalyzedGroupType) {
    var content: String = ""

    override fun toString(): String {
        return "AnalyzedGroup(groupType=$groupType, content='$content')"
    }
}

enum class AnalyzedGroupType {
    KEY,
    PRICE
}