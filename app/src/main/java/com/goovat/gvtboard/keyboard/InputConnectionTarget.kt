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

    override fun moveCursorLeft() {
        sendKeyEvent(KeyEvent.KEYCODE_DPAD_LEFT)
    }

    override fun moveCursorRight() {
        sendKeyEvent(KeyEvent.KEYCODE_DPAD_RIGHT)
    }

    override fun moveCursorUp() {
        sendKeyEvent(KeyEvent.KEYCODE_DPAD_UP)
    }

    override fun moveCursorDown() {
        sendKeyEvent(KeyEvent.KEYCODE_DPAD_DOWN)
    }

    private fun sendKeyEvent(keyCode: Int) {
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                keyCode
            )
        )

        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_UP,
                keyCode
            )
        )
    }
}
