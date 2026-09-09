package com.goovat.gvtboard.keyboard

class SuggestionRankingPolicy(
    private val maxSuggestions: Int = 20
) {

    init {
        require(maxSuggestions > 0)
    }

    fun rank(
        candidates: List<SuggestionCandidate>
    ): List<SuggestionCandidate> =
        candidates
            .distinctBy { it.text }
            .sortedByDescending { it.score }
            .take(maxSuggestions)
}
