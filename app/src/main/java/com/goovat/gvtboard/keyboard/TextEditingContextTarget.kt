package com.goovat.gvtboard.keyboard

interface TextEditingContextTarget {

    fun getTextBeforeCursor(maxChars: Int): String

    fun getSelectedText(maxChars: Int): String

    fun getTextAfterCursor(maxChars: Int): String

    fun readContext(maxBeforeCursorChars: Int, maxSelectedChars: Int, maxAfterCursorChars: Int): TextEditingContext =
        TextEditingContext(
            textBeforeCursor = getTextBeforeCursor(maxBeforeCursorChars),
            selectedText = getSelectedText(maxSelectedChars),
            textAfterCursor = getTextAfterCursor(maxAfterCursorChars)
        )
}
