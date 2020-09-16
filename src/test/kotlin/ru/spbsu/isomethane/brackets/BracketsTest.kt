package ru.spbsu.isomethane.brackets

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BracketsTest {
    @Test
    fun testEmptyString() {
        assertEquals("", insertBrackets("", listOf('['), insertEdges = false, insertCenter = false))
        assertEquals("[]", insertBrackets("", listOf('['), insertEdges = false, insertCenter = true))
        assertEquals("()", insertBrackets("", listOf('('), insertEdges = true, insertCenter = false))
        assertEquals("{}", insertBrackets("", listOf('{'), insertEdges = true, insertCenter = true))
    }

    @Test
    fun testEvenEdgeCenter() {
        assertEquals("(a[]b)", insertBrackets("ab", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
        assertEquals("(a[b{}c]d)", insertBrackets("abcd", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
        assertEquals("(a[b{c()d}e]f)", insertBrackets("abcdef", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
        assertEquals("(a[b{c(d[]e)f}g]h)", insertBrackets("abcdefgh", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
    }

    @Test
    fun testEvenEdgeNoCenter() {
        assertEquals("(ab)", insertBrackets("ab", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
        assertEquals("(a[bc]d)", insertBrackets("abcd", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
        assertEquals("(a[b{cd}e]f)", insertBrackets("abcdef", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
        assertEquals("(a[b{c(de)f}g]h)", insertBrackets("abcdefgh", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
    }

    @Test
    fun testEvenNoEdgeCenter() {
        assertEquals("a()b", insertBrackets("ab", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
        assertEquals("a(b[]c)d", insertBrackets("abcd", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
        assertEquals("a(b[c{}d]e)f", insertBrackets("abcdef", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
        assertEquals("a(b[c{d()e}f]g)h", insertBrackets("abcdefgh", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
    }

    @Test
    fun testEvenNoEdgeNoCenter() {
        assertEquals("ab", insertBrackets("ab", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
        assertEquals("a(bc)d", insertBrackets("abcd", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
        assertEquals("a(b[cd]e)f", insertBrackets("abcdef", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
        assertEquals("a(b[c{de}f]g)h", insertBrackets("abcdefgh", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
    }

    @Test
    fun testOddEdgeCenter() {
        assertEquals("(a)", insertBrackets("a", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
        assertEquals("(a[b]c)", insertBrackets("abc", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
        assertEquals("(a[b{c}d]e)", insertBrackets("abcde", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
        assertEquals("(a[b{c(d)e}f]g)", insertBrackets("abcdefg", listOf('(', '[', '{'), insertEdges = true, insertCenter = true))
    }

    @Test
    fun testOddEdgeNoCenter() {
        assertEquals("(a)", insertBrackets("a", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
        assertEquals("(a[b]c)", insertBrackets("abc", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
        assertEquals("(a[b{c}d]e)", insertBrackets("abcde", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
        assertEquals("(a[b{c(d)e}f]g)", insertBrackets("abcdefg", listOf('(', '[', '{'), insertEdges = true, insertCenter = false))
    }

    @Test
    fun testOddNoEdgeCenter() {
        assertEquals("a", insertBrackets("a", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
        assertEquals("a(b)c", insertBrackets("abc", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
        assertEquals("a(b[c]d)e", insertBrackets("abcde", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
        assertEquals("a(b[c{d}e]f)g", insertBrackets("abcdefg", listOf('(', '[', '{'), insertEdges = false, insertCenter = true))
    }

    @Test
    fun testOddNoEdgeNoCenter() {
        assertEquals("a", insertBrackets("a", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
        assertEquals("a(b)c", insertBrackets("abc", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
        assertEquals("a(b[c]d)e", insertBrackets("abcde", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
        assertEquals("a(b[c{d}e]f)g", insertBrackets("abcdefg", listOf('(', '[', '{'), insertEdges = false, insertCenter = false))
    }

    @Test
    fun testOneBracket() {
        assertEquals("a", insertBrackets("a", listOf('('), insertEdges = false, insertCenter = false))
        assertEquals("(ab)", insertBrackets("ab", listOf('('), insertEdges = true, insertCenter = false))
        assertEquals("a(b)c", insertBrackets("abc", listOf('('), insertEdges = false, insertCenter = true))
        assertEquals("(a(b()c)d)", insertBrackets("abcd", listOf('('), insertEdges = true, insertCenter = true))
        assertEquals("a(b(c)d)e", insertBrackets("abcde", listOf('('), insertEdges = false, insertCenter = false))
        assertEquals("(a(b(cd)e)f)", insertBrackets("abcdef", listOf('('), insertEdges = true, insertCenter = false))
        assertEquals("a(b(c(d)e)f)g", insertBrackets("abcdefg", listOf('('), insertEdges = false, insertCenter = true))
        assertEquals("(a(b(c(d()e)f)g)h)", insertBrackets("abcdefgh", listOf('('), insertEdges = true, insertCenter = true))
    }

    @Test
    fun testManyBrackets() {
        assertEquals("(a)", insertBrackets("a", listOf('(', '[', '['), insertEdges = true, insertCenter = false))
        assertEquals("(a[b[c(d[]e)f]g]h)", insertBrackets("abcdefgh", listOf('(', '[', '['), insertEdges = true, insertCenter = true))
    }

    @Test
    fun testNoBrackets() {
        assertThrows(IllegalArgumentException::class.java) {
            insertBrackets("abc", listOf(), insertEdges = true, insertCenter = false)
        }
    }

    @Test
    fun testIncorrectBrackets() {
        assertThrows(RuntimeException::class.java) {
            insertBrackets("abc", listOf(')'), insertEdges = true, insertCenter = false)
        }
        assertThrows(RuntimeException::class.java) {
            insertBrackets("abc", listOf('(', '.'), insertEdges = true, insertCenter = false)
        }
    }
}