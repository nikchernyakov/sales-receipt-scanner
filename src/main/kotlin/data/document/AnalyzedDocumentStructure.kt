package data.document

import java.lang.StringBuilder

class AnalyzedDocument<T : Token>(var lines: List<AnalyzedLine<T>>) {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("AnalyzedDocument:\n")
        lines.forEach { stringBuilder.append("  ").append(it.toString()).append("\n")}

        return stringBuilder.toString()
    }
}

class AnalyzedLine<T : Token>(var tokens: List<T>) {
    override fun toString(): String {
        return "AnalyzedLine(tokens=$tokens)"
    }
}

open class Token(var type: TokenType, var tab: Tab) {
    var content: String = ""

    override fun toString(): String {
        return "Token($type, $tab, '$content')"
    }
}

class VarToken(type: TokenType, tab: Tab, val candidates: List<TokenType>): Token(type, tab) {
    override fun toString(): String {
        return "VarToken($type, $tab, '$content', $candidates)"
    }
}

enum class TokenType {
    KEY,
    PRICE,
    TRASH,
    NUMBER,
    COUNT,
    UNKNOWN
}