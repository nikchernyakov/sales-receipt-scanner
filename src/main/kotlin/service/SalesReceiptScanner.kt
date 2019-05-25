package service

import analyze.scanned.ScannedDocVarAnalyzer
import analyze.token.TokenVarAnalyzer
import data.document.PurchaseInfo
import data.document.ReceiptDocument
import data.document.ShopInfo
import parser.OcrHtmlParser
import tess.TesseractService
import java.io.File

class SalesReceiptScanner {

    private var tesseractService = TesseractService()
    private val scannedDocumentAnalyzer = ScannedDocVarAnalyzer()
    private val tokenAnalyzer = TokenVarAnalyzer()

    fun doScan(imageFile: File): ReceiptDocument {
        val ocrHtmlContent = tesseractService.doOcr(imageFile)
        val scannedDocument = OcrHtmlParser.parseOcrHtml(ocrHtmlContent)
        println(scannedDocument.toString())
        val analyzedDocument = scannedDocumentAnalyzer.analyzeDocument(scannedDocument)
        println(analyzedDocument.toString())
        val result = tokenAnalyzer.analyzeItems(analyzedDocument)
        result.forEach { println(it.toString()) }
        return ReceiptDocument(ShopInfo("Not implemented yet"),
                PurchaseInfo(0f, result.toMutableList()))
    }

}