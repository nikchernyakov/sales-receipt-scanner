package service

import analyze.scanned.ScannedDocVarAnalyzer
import analyze.token.TokenVarAnalyzer
import data.document.PurchaseInfo
import data.document.ReceiptDocument
import data.document.ShopInfo
import parser.OcrHtmlParser
import tess.TesseractService
import java.io.File

class SalesReceiptScanner(tessDataPath: String) {

    private var tesseractService = TesseractService(tessDataPath)
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

    fun doScan(imageFile: File, resultFile: File): ReceiptDocument {
        var resultTime = 0f
        var startTime = System.currentTimeMillis()
        val ocrHtmlContent = tesseractService.doOcr(imageFile)
        val scannedDocument = OcrHtmlParser.parseOcrHtml(ocrHtmlContent)
        resultTime += System.currentTimeMillis() - startTime
        resultFile.appendText(scannedDocument.toString() + "\n\n\n")
        startTime = System.currentTimeMillis()
        val analyzedDocument = scannedDocumentAnalyzer.analyzeDocument(scannedDocument)
        resultTime += System.currentTimeMillis() - startTime
        resultFile.appendText(analyzedDocument.toString() + "\n\n\n")
        startTime = System.currentTimeMillis()
        val result = tokenAnalyzer.analyzeItems(analyzedDocument)
        resultTime += System.currentTimeMillis() - startTime
        //result.forEach { resultFile.writeText((it.toString() + "\n")) }
        resultFile.appendText("Test time: ${resultTime/1000} \n\n")
        return ReceiptDocument(ShopInfo("Not implemented yet"),
                PurchaseInfo(0f, result.toMutableList()))
    }

}