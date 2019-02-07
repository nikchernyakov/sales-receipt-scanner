package analyze

import data.document.*

class ScannedDocumentAnalyzer {
    fun analyzeLine(line: ScannedLine): AnalyzedBlock {
        val analyzedBlock = AnalyzedBlock(ArrayList())
        var previousGroup: AnalyzedGroup
        line.content.words.forEach {

        }
        return analyzedBlock
    }

    fun resolveGroupType(word: ScannedWord, previousGroup: AnalyzedGroup): AnalyzedGroupType {
        return AnalyzedGroupType.KEY
    }
}