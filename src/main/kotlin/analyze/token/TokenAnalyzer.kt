package analyze.token

import data.document.AnalyzedDocument
import data.document.PurchaseItem
import data.document.Token

interface TokenAnalyzer<T : Token> {
    fun analyzeItems(document: AnalyzedDocument<T>): List<PurchaseItem>
}