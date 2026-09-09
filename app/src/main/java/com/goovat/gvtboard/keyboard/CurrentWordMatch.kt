package com.goovat.gvtboard.keyboard

data class CurrentWordMatch(
    val text: String,
    val charactersBeforeCursor: Int,
    val charactersAfterCursor: Int
) {
    init {
        require(text.isNotEmpty())
        require(charactersBeforeCursor >= 0)
        require(charactersAfterCursor >= 0)
    }
}
