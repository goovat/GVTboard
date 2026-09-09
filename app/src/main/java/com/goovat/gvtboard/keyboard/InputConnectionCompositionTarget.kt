package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection

class InputConnectionCompositionTarget(
    private val inputConnection: InputConnection
) : TextCompositionTarget {

    override fun setComposingText(text: String) {
        inputConnection.setComposingText(text, 1)
    }

    override fun finishComposingText() {
        inputConnection.finishComposingText()
    }

    override fun commitText(text: String) {
        inputConnection.commitText(text, 1)
    }
}
