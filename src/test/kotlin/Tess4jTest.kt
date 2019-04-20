import analyze.ScannedDocumentAnalyzer
import analyze.TokenAnalyzer
import data.document.AnalyzedDocument
import org.junit.Before
import org.junit.Test
import parser.OcrHtmlParser
import tess.TesseractService
import java.io.File

const val IMAGE_ROOT_PATH = "src/test/resources/images/"

class Tess4jTest {

    private lateinit var tesseractService: TesseractService
    private val scannedDocumentAnalyzer = ScannedDocumentAnalyzer()
    private val tokenAnalyzer = TokenAnalyzer()

    @Before
    fun init() {
        tesseractService = TesseractService()
    }

    @Test
    fun test1() {
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img-4/1-2.jpg")
        val ocrHtmlContent = tesseractService.doOcr(imageFile)
        val scannedDocument = OcrHtmlParser.parseOcrHtml(ocrHtmlContent)
        println(scannedDocument.toString())
    }

    @Test
    fun testWithToken() {
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img-4/1-2.jpg")
        val ocrHtmlContent = tesseractService.doOcr(imageFile)
        val scannedDocument = OcrHtmlParser.parseOcrHtml(ocrHtmlContent)
        println(scannedDocument.toString())
        var analyzedDocument = AnalyzedDocument(scannedDocumentAnalyzer.analyzeDocument(scannedDocument))
        var result = tokenAnalyzer.analyzeItems(analyzedDocument)
        result.forEach { println(it.toString()) }
    }
}