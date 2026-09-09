package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class SuggestionRowPolicyTest {

    @Test
    fun keepsSuggestionsInProvidedOrder() {
        val suggestions = listOf(
            SuggestionCandidate("world", 0.9),
            SuggestionCandidate("word", 0.8),
            SuggestionCandidate("work", 0.7)
        )

        val state =
            SuggestionRowPolicy()
                .createState(suggestions)

        assertEquals(
            suggestions,
            state.suggestions
        )
    }

    @Test
    fun limitsVisibleSuggestionsToTwenty() {
        val suggestions =
            (1..25).map { index ->
                SuggestionCandidate(
                    text = "word$index",
                    score = index.toDouble()
                )
            }

        val state =
            SuggestionRowPolicy()
                .createState(suggestions)

        assertEquals(
            20,
            state.suggestions.size
        )

        assertEquals(
            "word1",
            state.suggestions.first().text
        )

        assertEquals(
            "word20",
            state.suggestions.last().text
        )
    }

    @Test
    fun supportsCustomVisibleSuggestionLimit() {
        val suggestions =
            (1..10).map { index ->
                SuggestionCandidate(
                    text = "word$index"
                )
            }

        val state =
            SuggestionRowPolicy(
                maxVisibleSuggestions = 5
            ).createState(suggestions)

        assertEquals(
            5,
            state.suggestions.size
        )
    }

    @Test
    fun acceptsFewerSuggestionsThanMaximum() {
        val suggestions = listOf(
            SuggestionCandidate("hello"),
            SuggestionCandidate("help")
        )

        val state =
            SuggestionRowPolicy()
                .createState(suggestions)

        assertEquals(
            suggestions,
            state.suggestions
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectsNonPositiveVisibleSuggestionLimit() {
        SuggestionRowPolicy(
            maxVisibleSuggestions = 0
        )
    }
}
