import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.Tesseract1
import net.sourceforge.tess4j.TesseractException
import net.sourceforge.tess4j.util.LoadLibs
import org.junit.Test
import java.io.File

const val IMAGE_ROOT_PATH = "src/test/resources/images/"

class Tess4jTest {

    @Test
    fun test1() {
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img1.jpg")
        val tesseractInstance = Tesseract1()
        tesseractInstance.setLanguage("rus")
        tesseractInstance.setDatapath(LoadLibs.extractTessResources("rus.traineddata").parent)


        try {
            tesseractInstance.setHocr(true)

            val result = tesseractInstance.doOCR(imageFile)
            println(result)
        } catch (ex: TesseractException) {
            println(ex.message)
        }
    }
}