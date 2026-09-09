package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.SuggestionRowSelectionHandler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SuggestionRowSelectionHandlerTest {

    @Test
    fun delegatesSelectedSuggestionToCoordinator() {
        val coordinator =
            FakeSuggestionSelectionCoordinator(true)

        val handler =
            SuggestionRowSelectionHandler(coordinator)

        val suggestion =
            SuggestionCandidate("world")

        val result =
            handler.handle(suggestion)

        assertTrue(result)
        assertEquals(
            suggestion,
            coordinator.selectedSuggestion
        )
        assertEquals(
            1,
            coordinator.callCount
        )
    }

    @Test
    fun returnsFailureFromCoordinator() {
        val coordinator =
            FakeSuggestionSelectionCoordinator(false)

        val handler =
            SuggestionRowSelectionHandler(coordinator)

        val result =
            handler.handle(
                SuggestionCandidate("world")
            )

        assertFalse(result)
        assertEquals(
            1,
            coordinator.callCount
        )
    }

    private class FakeSuggestionSelectionCoordinator(
        private val result: Boolean
    ) : SuggestionSelectionCoordinatorService {

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
