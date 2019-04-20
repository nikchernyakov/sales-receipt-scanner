package analyze

import analyze.scanned.ScannedDocExactAnalyzer
import analyze.token.TokenExactAnalyzer
import org.junit.Test
import parser.TextParser

class TokenAnalyzerTest {

    private val scannedDocumentAnalyzer = ScannedDocExactAnalyzer()
    private val textParser = TextParser()
    private val tokenAnalyzer = TokenExactAnalyzer()

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