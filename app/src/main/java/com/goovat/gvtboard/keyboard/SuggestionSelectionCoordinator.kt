package com.goovat.gvtboard.keyboard

class SuggestionSelectionCoordinator(
    private val selectionService: SuggestionSelectionTargetService
) {

    fun select(
        suggestion: SuggestionCandidate
    ): Boolean =
        selectionService.select(suggestion)
}
