import org.junit.Before
import org.junit.Test
import service.SalesReceiptScanner
import java.io.File

class ScanTest {

    private lateinit var salesReceiptScanner: SalesReceiptScanner

    @Before
    fun init() {
        salesReceiptScanner = SalesReceiptScanner("src/test/resources/tess4j/data")
    }

    @Test
    fun testWithResult() {
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img-8/1.png")
        salesReceiptScanner.doScan(imageFile)
    }

    @Test
    fun testImageFolder() {
        // Get originals
        val folder = File("../testFolder/original")
        val files = folder.listFiles()

        // Clean old results
        val copyFolder = File("../testFolder/copy").deleteRecursively()
        val resultFolder = File("../testFolder/result").deleteRecursively()
        File("../testFolder/result").mkdir()

        // Copy original and create result file
        val testResults = files.mapIndexed { index, file ->
            file.copyTo(File("../testFolder/copy/$index.jpg"))
            File("../testFolder/result/$index-result")
        }

        // Make OCR for all files and write result in file
        files.forEachIndexed { index, file ->
            testResults[index].appendText(
                    try {
                        salesReceiptScanner.doScan(file, testResults[index]).purchaseInfo.items.toString()
                    } catch (ex: Exception) {
                        ex.message ?: ex.toString()
                    })
        }
    }
}