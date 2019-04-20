package analyze

import data.document.AnalyzedDocument
import org.junit.Test
import parser.TextParser

class TokenAnalyzerTest {

    private val scannedDocumentAnalyzer = ScannedDocumentAnalyzer()
    private val textParser = TextParser()
    private val tokenAnalyzer = TokenAnalyzer()

    @Test
    fun test1() {
        /*var document = textParser.parseText(
                """Сок ябл добрый 50.12
                  |Молоко 30.49
                """.trimMargin())
        var analyzedDocument = AnalyzedDocument(scannedDocumentAnalyzer.analyzeDocument(document))
        var result = tokenAnalyzer.analyzeItems(analyzedDocument)
        result.forEach { println(it.toString()) }*/
    }
}