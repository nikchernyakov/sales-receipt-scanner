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

class AnalyzedLine<T : Token>(var elements: List<AnalyzedElement<T>>) {
    override fun toString(): String {
        return "AnalyzedLine($elements)"
    }
}

class AnalyzedElement<T: Token>(var token: T) : InlineElement<AnalyzedElement<T>> {
    override val neighbor = Neighbor<AnalyzedElement<T>>()

    override fun toString(): String {
        return token.toString()
    }
}

open class Token() {
    var content: String = ""
    var type: TokenType = TokenType.UNKNOWN
    var tab: Tab = Tab(0, 0)

    constructor(_type: TokenType, _tab: Tab) : this() {
        type = _type
        tab = _tab
    }

    override fun toString(): String {
        return "Token($type, $tab, '$content')"
    }
}

class VarToken(): Token() {

    var candidates: List<TokenType> = ArrayList()

    constructor(_type: TokenType, _tab: Tab, _candidates: List<TokenType>) : this() {
        type = _type
        tab = _tab
        candidates = _candidates
    }

    override fun toString(): String {
        return "VarToken($type, $tab, '$content', $candidates)"
    }
}

enum class TokenType {
    WORD,
    KEY,
    PRICE,
    TRASH,
    NUMBER,
    COUNT,
    PERCENT,
    UNKNOWN
}