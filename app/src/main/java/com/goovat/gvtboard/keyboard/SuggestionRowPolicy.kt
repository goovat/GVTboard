package com.goovat.gvtboard.keyboard

class SuggestionRowPolicy(
    private val maxVisibleSuggestions: Int = 20
) {

    init {
        require(maxVisibleSuggestions > 0)
    }

    fun createState(
        suggestions: List<SuggestionCandidate>
    ): SuggestionRowState =
        SuggestionRowState(
            suggestions = suggestions
                .take(maxVisibleSuggestions)
        )
}
