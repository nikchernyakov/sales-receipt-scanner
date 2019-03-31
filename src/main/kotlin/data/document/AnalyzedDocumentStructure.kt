package data.document

class AnalyzedDocument(var lines: List<AnalyzedLine>)

class AnalyzedLine(var tokens: List<Token>) {
    override fun toString(): String {
        return "AnalyzedLine(tokens=$tokens)"
    }
}

class Token(var type: TokenType) {
    var content: String = ""

    override fun toString(): String {
        return "Token(type=$type, content='$content')"
    }
}

enum class TokenType {
    KEY,
    PRICE,
    TRASH,
    NUMBER
}