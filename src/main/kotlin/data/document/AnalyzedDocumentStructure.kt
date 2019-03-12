package data.document

class AnalyzedDocument(var blocks: List<AnalyzedBlock>)

class AnalyzedBlock(var tokens: List<Token>) {
    override fun toString(): String {
        return "AnalyzedBlock(tokens=$tokens)"
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
    PRICE
}