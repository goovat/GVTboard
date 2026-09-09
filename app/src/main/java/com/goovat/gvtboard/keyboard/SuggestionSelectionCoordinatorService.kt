package com.goovat.gvtboard.keyboard

interface SuggestionSelectionCoordinatorService {

    fun select(
        suggestion: SuggestionCandidate
    ): Boolean
}
