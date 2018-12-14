package tess

import net.sourceforge.tess4j.Tesseract1
import net.sourceforge.tess4j.TesseractException
import net.sourceforge.tess4j.util.LoadLibs
import java.io.File

class TesseractService {

    private var tesseractInstance: Tesseract1 = Tesseract1()

    init {
        tesseractInstance.setLanguage("rus")
        tesseractInstance.setDatapath(LoadLibs.extractTessResources("rus.traineddata").parent)
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