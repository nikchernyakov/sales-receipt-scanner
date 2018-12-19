import org.junit.Before
import org.junit.Test
import parser.OcrHtmlParser
import tess.TesseractService
import java.io.File

const val IMAGE_ROOT_PATH = "src/test/resources/images/"

class Tess4jTest {

    private lateinit var tesseractService: TesseractService

    @Before
    fun init() {
        tesseractService = TesseractService()
    }

    @Test
    fun test1() {
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img4.jpg")
        val ocrHtmlContent = tesseractService.doOcr(imageFile)
        val scannedDocument = OcrHtmlParser.parseOcrHtml(ocrHtmlContent)
        println(scannedDocument.toDocumentString())
    }
}