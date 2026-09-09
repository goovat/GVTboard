package com.goovat.gvtboard.keyboard

data class TextEditingContext(
    val textBeforeCursor: String = "",
    val selectedText: String = "",
    val textAfterCursor: String = ""
)
