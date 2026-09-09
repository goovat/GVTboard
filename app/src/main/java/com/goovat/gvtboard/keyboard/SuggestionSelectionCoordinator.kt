package com.goovat.gvtboard.keyboard

class SuggestionSelectionCoordinator(
    private val selectionService: SuggestionSelectionTargetService
) : SuggestionSelectionCoordinatorService {

    override fun select(
        suggestion: SuggestionCandidate
    ): Boolean =
        selectionService.select(suggestion)
}
