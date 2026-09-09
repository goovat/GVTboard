package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection

class InputConnectionSuggestionSelectionTarget(
    private val inputConnection: InputConnection
) : SuggestionSelectionTarget {

    override fun replaceCurrentWord(
        currentWord: String,
        replacement: String
    ) {
        if (currentWord.isEmpty()) {
            return
        }

        val codePointCount =
            currentWord.codePointCount(
                0,
                currentWord.length
            )

        if (!inputConnection.deleteSurroundingTextInCodePoints(
                codePointCount,
                0
            )
        ) {
            inputConnection.deleteSurroundingText(
                currentWord.length,
                0
            )
        }

        inputConnection.commitText(
            replacement,
            1
        )
    }
}
