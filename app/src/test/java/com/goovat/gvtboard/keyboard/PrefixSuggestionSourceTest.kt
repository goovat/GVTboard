package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class PrefixSuggestionSourceTest {

    @Test
    fun returnsWordsMatchingCurrentWordPrefix() {
        val source = PrefixSuggestionSource(
            listOf(
                "world",
                "work",
                "word",
                "hello"
            )
        )

        val result = source.suggest(
            currentWord = "wor",
            context = TextEditingContext()
        )

        assertEquals(
            listOf("world", "work", "word"),
            result.map { it.text }
        )
    }

    @Test
    fun matchingIsCaseInsensitive() {
        val source = PrefixSuggestionSource(
            listOf(
                "World",
                "WORK",
                "hello"
            )
        )

        val result = source.suggest(
            currentWord = "wor",
            context = TextEditingContext()
        )

        assertEquals(
            listOf("World", "WORK"),
            result.map { it.text }
        )
    }

    @Test
    fun doesNotReturnCurrentWordItself() {
        val source = PrefixSuggestionSource(
            listOf(
                "wor",
                "word",
                "world"
            )
        )

        val result = source.suggest(
            currentWord = "wor",
            context = TextEditingContext()
        )

        assertEquals(
            listOf("word", "world"),
            result.map { it.text }
        )
    }

    @Test
    fun ignoresBlankWordsAndCaseInsensitiveDuplicates() {
        val source = PrefixSuggestionSource(
            listOf(
                "",
                " ",
                "World",
                "world",
                "WORLD"
            )
        )

        val result = source.suggest(
            currentWord = "wor",
            context = TextEditingContext()
        )

        assertEquals(
            listOf("World"),
            result.map { it.text }
        )
    }

    @Test
    fun returnsEmptyForEmptyCurrentWord() {
        val source = PrefixSuggestionSource(
            listOf("hello", "world")
        )

        assertEquals(
            emptyList<SuggestionCandidate>(),
            source.suggest(
                currentWord = "",
                context = TextEditingContext()
            )
        )
    }

    @Test
    fun producesLongerWordsWithLowerScores() {
        val source = PrefixSuggestionSource(
            listOf(
                "world",
                "word"
            )
        )

        val result = source.suggest(
            currentWord = "wor",
            context = TextEditingContext()
        )

        assertEquals(
            0.3333333333333333,
            result[0].score,
            0.0
        )

        assertEquals(
            0.5,
            result[1].score,
            0.0
        )
    }
}
