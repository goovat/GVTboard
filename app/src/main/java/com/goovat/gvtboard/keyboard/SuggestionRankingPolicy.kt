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
            .groupBy { it.text }
            .values
            .map { duplicateCandidates ->
                duplicateCandidates.maxBy { it.score }
            }
            .sortedByDescending { it.score }
            .take(maxSuggestions)
}
