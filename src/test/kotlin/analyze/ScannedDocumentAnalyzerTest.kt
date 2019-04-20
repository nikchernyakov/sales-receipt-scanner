package analyze

import org.junit.Assert
import org.junit.Test
import parser.TextParser

class ScannedDocumentAnalyzerTest {

    private val scannedDocumentAnalyzer = ScannedDocumentAnalyzer()
    private val textParser = TextParser()

    @Test
    fun checkPriceTest() {
        Assert.assertFalse(scannedDocumentAnalyzer.checkPriceType("Lo123.41"))
        Assert.assertFalse(scannedDocumentAnalyzer.checkPriceType(".41"))
        Assert.assertFalse(scannedDocumentAnalyzer.checkPriceType("41"))
        Assert.assertFalse(scannedDocumentAnalyzer.checkPriceType("41.1"))

        Assert.assertTrue(scannedDocumentAnalyzer.checkPriceType("41.12"))
        Assert.assertTrue(scannedDocumentAnalyzer.checkPriceType("0.12"))
        Assert.assertTrue(scannedDocumentAnalyzer.checkPriceType("100.12"))
    }

    @Test
    fun analyzeLineTest() {
        /*var line = textParser.parseLine("Сок ябл добрый 50.12")
        println(scannedDocumentAnalyzer.analyzeLine(line))
        line = textParser.parseLine("Яблоки 12 кг 100.00")
        println(scannedDocumentAnalyzer.analyzeLine(line))
        line = textParser.parseLine("100.00 Пакет")
        println(scannedDocumentAnalyzer.analyzeLine(line))*/
    }

}