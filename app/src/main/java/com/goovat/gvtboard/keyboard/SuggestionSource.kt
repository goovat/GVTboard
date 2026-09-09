package com.goovat.gvtboard.keyboard

interface SuggestionSource {

    fun suggest(
        currentWord: String,
        context: TextEditingContext
    ): List<SuggestionCandidate>
}
