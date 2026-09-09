package com.goovat.gvtboard.keyboard

interface SuggestionSelectionTargetService {

    fun select(
        suggestion: SuggestionCandidate
    ): Boolean
}
