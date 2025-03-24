import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.system.exitProcess

class Lox {
    private var hadError = false

    fun main(args : Array<String>) {
        if ( args.size > 1 ) {
            println("Usage: klox [script]")
            exitProcess(1)
        } else if ( args.size ==1 ) {
            runFile(args[0])
        } else {
            runPrompt();
        }
    }

    private fun runFile(file: String) {
        val bytes = Files.readAllBytes(Paths.get(file))
        run(String(bytes, Charset.defaultCharset()))

        if (hadError) {
            exitProcess(65)
        }
    }


    private fun runPrompt() {
        val input = InputStreamReader(System.`in`)
        val reader = BufferedReader(input)

        for (line in reader.readLines()) {
            print("> ")
            if ( line.isEmpty() ) {
                break
            }
            run(line)
            hadError = false
        }
    }

    private fun run(source: String) {
        val scanner = Scanner(source)
        val tokens = scanner.tokens()

        for ( token in tokens) {
            println(token)
        }
    }

    fun error(line: Int, message: String) {
        report(line,message,"")
    }

    private fun report(line: Int, message: String, where: String) {
        println("[line $line] Error $where: $message")
        hadError = true
    }
}

