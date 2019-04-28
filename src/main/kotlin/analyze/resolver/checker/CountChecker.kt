package analyze.resolver.checker

import data.document.ScannedWord
import data.document.TokenType

object CountChecker : TypeChecker {
    override fun check(word: ScannedWord): TokenType? {
        if (word.content.length > 4) return null

        // TODO: can contains * or something else like "1*"
        var dotIndex = -1
        word.content.forEachIndexed { index, element ->
            if (element == '.') {
                dotIndex = index
            } else {
                if (!element.isDigit()) return null
            }
        }

        val hasStar = word.neighbor.previous?.content == "*"
                || word.neighbor.next?.content == "*"
        return if (dotIndex == -1
                || dotIndex in 1 until (word.content.lastIndex - 1)
                || hasStar)
            TokenType.PRICE else null
    }
}