package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.SuggestionCandidate
import com.goovat.gvtboard.keyboard.SuggestionSelectionCoordinatorService

class SuggestionRowSelectionHandler(
    private val coordinator: SuggestionSelectionCoordinatorService
) {

    fun handle(
        suggestion: SuggestionCandidate
    ): Boolean =
        coordinator.select(suggestion)
}
