package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CurrentWordMatcherTest {

    private val matcher =
        CurrentWordMatcher()

    @Test
    fun matchesWordWhenCursorIsAtEnd() {
        val result =
            matcher.match(
                TextEditingContext(
                    textBeforeCursor = "hello wor",
                    textAfterCursor = ""
                )
            )

        assertEquals(
            CurrentWordMatch(
                text = "wor",
                charactersBeforeCursor = 3,
                charactersAfterCursor = 0
            ),
            result
        )
    }

    @Test
    fun matchesWholeWordWhenCursorIsInsideWord() {
        val result =
            matcher.match(
                TextEditingContext(
                    textBeforeCursor = "hello wor",
                    textAfterCursor = "ld"
                )
            )

        assertEquals(
            CurrentWordMatch(
                text = "world",
                charactersBeforeCursor = 3,
                charactersAfterCursor = 2
            ),
            result
        )
    }

    @Test
    fun matchesWordWhenCursorIsAtBeginning() {
        val result =
            matcher.match(
                TextEditingContext(
                    textBeforeCursor = "hello ",
                    textAfterCursor = "world"
                )
            )

        assertEquals(
            CurrentWordMatch(
                text = "world",
                charactersBeforeCursor = 0,
                charactersAfterCursor = 5
            ),
            result
        )
    }

    @Test
    fun supportsApostrophes() {
        val result =
            matcher.match(
                TextEditingContext(
                    textBeforeCursor = "can",
                    textAfterCursor = "'t"
                )
            )

        assertEquals("can't", result?.text)
    }

    @Test
    fun supportsNumbers() {
        val result =
            matcher.match(
                TextEditingContext(
                    textBeforeCursor = "version",
                    textAfterCursor = "2"
                )
            )

        assertEquals("version2", result?.text)
    }

    @Test
    fun matchesWordWhenCursorIsBeforeWord() {
        val result =
            matcher.match(
                TextEditingContext(
                    textBeforeCursor = "hello ",
                    textAfterCursor = "world"
                )
            )

        assertEquals("world", result?.text)
    }

    @Test
    fun returnsNullForEmptyContext() {
        assertNull(
            matcher.match(
                TextEditingContext()
            )
        )
    }

    @Test
    fun returnsNullWhenTextIsSelected() {
        assertNull(
            matcher.match(
                TextEditingContext(
                    textBeforeCursor = "hello",
                    selectedText = "world",
                    textAfterCursor = ""
                )
            )
        )
    }
}
