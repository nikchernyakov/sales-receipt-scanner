package data.document

class AnalyzedDocument(var lines: List<AnalyzedLine>)

class AnalyzedLine(var tokens: List<Token>) {
    override fun toString(): String {
        return "AnalyzedLine(tokens=$tokens)"
    }
}

class Token(var type: TokenType, var tab: Tab) {
    var content: String = ""

    override fun toString(): String {
        return "Token($type, $tab, '$content')"
    }
}

enum class TokenType {
    KEY,
    PRICE,
    TRASH,
    NUMBER,
    COUNT
}