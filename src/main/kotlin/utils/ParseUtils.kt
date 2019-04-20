package utils

import data.document.Box
import data.document.Point

fun findDocumentName(documentTitle: String): String? {
    val imagePathStartIndex = documentTitle.indexOf('"')
    if (imagePathStartIndex == -1) return null

    val imagePathEndIndex = documentTitle.indexOf('"', imagePathStartIndex + 1)
    if (imagePathEndIndex == -1) return null

    return documentTitle.substring(imagePathStartIndex + 1, imagePathEndIndex)
}

fun findBox(title: String): Box {
    val boxData = ArrayList<Int>(4)
    var inBox = false
    for (word in title.split(" ")) {
        if (word == "bbox") {
            inBox = true
            continue
        }

        if (inBox) {
            if (word.last() == ';') {
                boxData.add(word.trimEnd(';').toInt())
                break
            }
            else {
                boxData.add(word.toInt())
            }
        }
    }

    return Box(
            Point(boxData[0], boxData[1]),
            Point(boxData[2], boxData[3])
    )
}