package com.goovat.gvtboard.keyboard

import android.view.KeyEvent
import android.view.inputmethod.InputConnection

class InputConnectionTarget(
    private val inputConnection: InputConnection
) : KeyInputTarget {

    override fun commitText(text: String) {
        inputConnection.commitText(text, 1)
    }

    override fun deleteBackward() {
        if (!inputConnection.deleteSurroundingTextInCodePoints(1, 0)) {
            inputConnection.deleteSurroundingText(1, 0)
        }
    }

    override fun sendEnter() {
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_ENTER
            )
        )

        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_UP,
                KeyEvent.KEYCODE_ENTER
            )
        )
    }
}
