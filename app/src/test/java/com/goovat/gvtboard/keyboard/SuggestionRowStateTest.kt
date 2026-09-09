package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class SuggestionRowStateTest {

    @Test
    fun storesSuggestionsInDisplayOrder() {
        val suggestions = listOf(
            SuggestionCandidate("world", 0.9),
            SuggestionCandidate("word", 0.8),
            SuggestionCandidate("work", 0.7)
        )

        val state =
            SuggestionRowState(suggestions)

        assertEquals(
            suggestions,
            state.suggestions
        )
    }

    @Test
    fun defaultsToEmptySuggestions() {
        val state = SuggestionRowState()

        assertEquals(
            emptyList<SuggestionCandidate>(),
            state.suggestions
        )
    }
}
