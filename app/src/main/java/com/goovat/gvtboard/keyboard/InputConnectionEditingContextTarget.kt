package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection

class InputConnectionEditingContextTarget(
    private val inputConnection: InputConnection
) : TextEditingContextTarget {

    override fun getTextBeforeCursor(maxChars: Int): String =
        inputConnection.getTextBeforeCursor(maxChars, 0)
            ?.toString()
            .orEmpty()

    override fun getSelectedText(maxChars: Int): String =
        inputConnection.getSelectedText(0)
            ?.toString()
            ?.take(maxChars)
            .orEmpty()

    override fun getTextAfterCursor(maxChars: Int): String =
        inputConnection.getTextAfterCursor(maxChars, 0)
            ?.toString()
            .orEmpty()
}
