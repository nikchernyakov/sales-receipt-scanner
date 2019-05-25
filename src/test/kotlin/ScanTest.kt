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
        val imageFile = File(IMAGE_ROOT_PATH + "sales-receipt-img-4/1-2.jpg")
        salesReceiptScanner.doScan(imageFile)
    }
}