package utils

fun isNumber(string: String): Boolean {
    return string
            .filterNot { it.isDigit() }
            .isEmpty()
}