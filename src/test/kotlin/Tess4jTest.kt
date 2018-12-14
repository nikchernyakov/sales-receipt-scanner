import org.junit.Before
import org.junit.Test
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
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img1.jpg")
        tesseractService.doOcr(imageFile)
    }
}