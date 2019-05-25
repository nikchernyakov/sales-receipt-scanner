import analyze.scanned.ScannedDocVarAnalyzer
import analyze.token.TokenVarAnalyzer
import org.junit.Before
import org.junit.Test
import parser.OcrHtmlParser
import tess.TesseractService
import java.io.File

const val IMAGE_ROOT_PATH = "src/test/resources/images/"

class Tess4jTest {

    private lateinit var tesseractService: TesseractService
    private val scannedDocumentAnalyzer = ScannedDocVarAnalyzer()
    private val tokenAnalyzer = TokenVarAnalyzer()

    @Before
    fun init() {
        tesseractService = TesseractService("src/test/resources/tess4j/data")
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
        println(analyzedDocument.toString())
        val result = tokenAnalyzer.analyzeItems(analyzedDocument)
        result.forEach { println(it.toString()) }
    }
}