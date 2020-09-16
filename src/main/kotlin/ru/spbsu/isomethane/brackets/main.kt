package ru.spbsu.isomethane.brackets

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import kotlin.system.exitProcess

class ProgramArgs(parser: ArgParser) {
    val string by parser.positional(
        "STRING",
        help = "string to transform")

    val edge: Boolean by parser.flagging(
        "-e", "--edge",
        help = "insert edge brackets")

    val center: Boolean by parser.flagging(
        "-c", "--center",
        help = "insert center brackets (for strings with even length)")

    val brackets: List<Char> by parser.storing(
        "--brackets",
        help = "opening brackets sequence",
        transform = { toList() })
}

fun main(args: Array<String>) = mainBody {
    ArgParser(args).parseInto(::ProgramArgs).run {
        try {
            println(insertBrackets(string, brackets, insertEdges = edge, insertCenter = center))
        } catch (e: Exception) {
            System.err.println(e.message)
            exitProcess(1)
        }
    }
}