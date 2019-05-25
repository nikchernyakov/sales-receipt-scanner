package tess

import net.sourceforge.tess4j.Tesseract1
import net.sourceforge.tess4j.TesseractException
import java.io.File

class TesseractService(dataPath: String) {

    private var tesseractInstance: Tesseract1 = Tesseract1()

    init {
        tesseractInstance.setDatapath(dataPath)
        tesseractInstance.setLanguage("rus")
        tesseractInstance.setHocr(true)
    }

    fun doOcr(file: File): String? {
        var result: String?
        try {
            result = tesseractInstance.doOCR(file)
            println(result)
        } catch (ex: TesseractException) {
            println(ex.message)
            result = null
        }
        return result
    }
}