package analyze.resolver.checker

import data.document.ScannedWord
import data.document.TokenType

interface TypeChecker {
    fun check(word: ScannedWord): TokenType?
}