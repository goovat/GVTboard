package com.goovat.gvtboard.keyboard

data class TextEditingContextPolicy(
    val maxBeforeCursorChars: Int = 64,
    val maxSelectedChars: Int = 32,
    val maxAfterCursorChars: Int = 64
) {
    init {
        require(maxBeforeCursorChars > 0)
        require(maxSelectedChars > 0)
        require(maxAfterCursorChars > 0)
    }
}
