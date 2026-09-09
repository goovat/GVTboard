package com.goovat.gvtboard.keyboard

interface SuggestionSelectionTarget {

    fun replaceCurrentWord(
        currentWord: String,
        replacement: String
    )
}
