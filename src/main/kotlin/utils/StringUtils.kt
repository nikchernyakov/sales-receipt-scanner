package utils

val SEPARATORS = listOf('-', ',', '.', '"', '\'')

val PERCENT_CANDIDATE = listOf(';', '%')

fun isNumber(string: String): Boolean {
    return string
            .filterNot { it.isDigit() }
            .isEmpty()
}

fun hasNumber(string: String): Boolean {
    var isPreviousDigit = false
    var hasNum = false
    string.forEach {
        val isDigit = it.isDigit()
        if (isDigit && !hasNum) hasNum = true
        if (isDigit && hasNum && !isPreviousDigit) return false

        isPreviousDigit = isDigit
    }
    return true
}

fun isWord(string: String): Boolean {
    var hasLetter = false
    string.forEachIndexed { _, c ->
        if (c.isLetter()) hasLetter = true

        if (!c.isLetter() && SEPARATORS.none { it == c }) return false
    }

    return hasLetter
}

fun compareSymbolWithList(char: Char, charsToCompare: List<Char> ): Boolean {
    return charsToCompare.any { char == it }
}