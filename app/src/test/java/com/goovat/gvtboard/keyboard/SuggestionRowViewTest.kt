package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class SuggestionRowViewTest {

    @Test
    fun suggestionRowStatePreservesSuggestionOrder() {
        val suggestions = listOf(
            SuggestionCandidate("world", 0.9),
            SuggestionCandidate("word", 0.8),
            SuggestionCandidate("work", 0.7)
        )

        val state =
            SuggestionRowState(suggestions)

        assertEquals(
            listOf("world", "word", "work"),
            state.suggestions.map { it.text }
        )
    }

    @Test
    fun suggestionRowStateCanRepresentEmptyRow() {
        val state = SuggestionRowState()

        assertEquals(
            emptyList<SuggestionCandidate>(),
            state.suggestions
        )
    }
}
