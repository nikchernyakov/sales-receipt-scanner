package analyze

import analyze.resolver.StrongTypeResolver
import analyze.scanned.ScannedDocExactAnalyzer
import org.junit.Assert
import org.junit.Test
import parser.TextParser

class ScannedDocumentAnalyzerTest {

    private val scannedDocumentAnalyzer = ScannedDocExactAnalyzer()
    private val textParser = TextParser()

    @Test
    fun checkPriceTest() {
        Assert.assertFalse(StrongTypeResolver.checkPriceType("Lo123.41"))
        Assert.assertFalse(StrongTypeResolver.checkPriceType(".41"))
        Assert.assertFalse(StrongTypeResolver.checkPriceType("41"))
        Assert.assertFalse(StrongTypeResolver.checkPriceType("41.1"))

        Assert.assertTrue(StrongTypeResolver.checkPriceType("41.12"))
        Assert.assertTrue(StrongTypeResolver.checkPriceType("0.12"))
        Assert.assertTrue(StrongTypeResolver.checkPriceType("100.12"))
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