package data.document

class AnalyzedDocument(var blocks: List<AnalyzedBlock>)

class AnalyzedBlock(var groups: List<AnalyzedGroup>)

class AnalyzedGroup(var groupType: AnalyzedGroupType) {
    var content: String = ""
}

enum class AnalyzedGroupType {
    KEY,
    PRICE
}