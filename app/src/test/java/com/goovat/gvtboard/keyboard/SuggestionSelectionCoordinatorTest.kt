package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SuggestionSelectionCoordinatorTest {

    @Test
    fun delegatesSuggestionSelection() {
        val service = FakeSuggestionSelectionService(true)
        val coordinator =
            SuggestionSelectionCoordinator(service)

        val suggestion =
            SuggestionCandidate("world")

        val result =
            coordinator.select(suggestion)

        assertTrue(result)
        assertEquals(
            suggestion,
            service.selectedSuggestion
        )
        assertEquals(1, service.callCount)
    }

    @Test
    fun returnsFailureFromSelectionService() {
        val service = FakeSuggestionSelectionService(false)
        val coordinator =
            SuggestionSelectionCoordinator(service)

        val result =
            coordinator.select(
                SuggestionCandidate("world")
            )

        assertFalse(result)
        assertEquals(1, service.callCount)
    }

    private class FakeSuggestionSelectionService(
        private val result: Boolean
    ) : SuggestionSelectionTargetService {

        var selectedSuggestion:
            SuggestionCandidate? = null
            private set

        var callCount: Int = 0
            private set

        override fun select(
            suggestion: SuggestionCandidate
        ): Boolean {
            callCount++
            selectedSuggestion = suggestion
            return result
        }
    }
}
