package ru.spbsu.isomethane.brackets

private fun Char.closingBracket(): Char {
    return when (this) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        else -> throw RuntimeException("$this is not an opening bracket")
    }
}

private fun Boolean.toInt(): Int = if (this) 1 else 0

private fun <T> repeatNTimes(items: Iterable<T>, n: Int): Sequence<T> = sequence {
    if (!items.iterator().hasNext()) {
        throw IllegalArgumentException("items must contain at list one element")
    }
    while (true) {
        yieldAll(items)
    }
}.take(n)

private fun insertDelimiters(word: String, delimiters: Sequence<Char>, insertFirst: Boolean): String {
    val wordIterator = word.iterator()
    val delimitersIterator = delimiters.iterator()
    val resultBuilder = StringBuilder()

    val appendNextChar: (Iterator<Char>) -> Unit = {
        if (it.hasNext()) {
            resultBuilder.append(it.next())
        }
    }

    if (insertFirst) {
        appendNextChar(delimitersIterator)
    }
    while (wordIterator.hasNext() || delimitersIterator.hasNext()) {
        appendNextChar(wordIterator)
        appendNextChar(delimitersIterator)
    }
    return resultBuilder.toString()
}

fun insertBrackets(word: String, brackets: Iterable<Char>, insertEdges: Boolean, insertCenter: Boolean): String {
    val numberOfBracketPairs = if (word.isEmpty()) {
        (insertEdges || insertCenter).toInt()
    } else {
        (word.length - 1 + insertCenter.toInt()) / 2 + insertEdges.toInt()
    }
    val bracketSequence = repeatNTimes(brackets, numberOfBracketPairs)
    val firstHalf = word.substring(0, word.length / 2)
    val secondHalf = word.substring(word.length / 2)

    return insertDelimiters(firstHalf, bracketSequence, insertEdges) +
            insertDelimiters(secondHalf.reversed(), bracketSequence.map { it.closingBracket() }, insertEdges).reversed()
}
