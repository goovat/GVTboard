package com.goovat.gvtboard.keyboard

data class SuggestionCandidate(
    val text: String,
    val score: Double = 0.0
) {
    init {
        require(text.isNotEmpty())
        require(score.isFinite())
    }
}
