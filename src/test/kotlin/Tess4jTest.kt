import analyze.scanned.ScannedDocExactAnalyzer
import analyze.token.TokenExactAnalyzer
import org.junit.Before
import org.junit.Test
import parser.OcrHtmlParser
import tess.TesseractService
import java.io.File

const val IMAGE_ROOT_PATH = "src/test/resources/images/"

class Tess4jTest {

    private lateinit var tesseractService: TesseractService
    private val scannedDocumentAnalyzer = ScannedDocExactAnalyzer()
    private val tokenAnalyzer = TokenExactAnalyzer()

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
    fun testWithAnalyze() {
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img-4/1-2.jpg")
        val ocrHtmlContent = tesseractService.doOcr(imageFile)
        val scannedDocument = OcrHtmlParser.parseOcrHtml(ocrHtmlContent)
        println(scannedDocument.toString())
        val analyzedDocument = scannedDocumentAnalyzer.analyzeDocument(scannedDocument)
        println(analyzedDocument.toString())
    }

    @Test
    fun testWithResult() {
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img-4/1-2.jpg")
        val ocrHtmlContent = tesseractService.doOcr(imageFile)
        val scannedDocument = OcrHtmlParser.parseOcrHtml(ocrHtmlContent)
        println(scannedDocument.toString())
        val analyzedDocument = scannedDocumentAnalyzer.analyzeDocument(scannedDocument)
        val result = tokenAnalyzer.analyzeItems(analyzedDocument)
        result.forEach { println(it.toString()) }
    }
}