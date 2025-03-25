abstract class Token {
    abstract var type: TokenType
    abstract var lexeme: String
    abstract var literal: Any
    abstract var line: Int

    fun Token(type: TokenType, lexeme: String, literal: Any, line: Int) {
        this.type = type
        this.lexeme = lexeme
        this.literal = literal
        this.line = line
    }

    override fun toString(): String {
        return "$type $lexeme $literal"
    }
}