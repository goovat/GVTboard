package com.goovat.gvtboard.keyboard

import android.view.KeyEvent
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection

class InputConnectionTarget(
    private val inputConnection: InputConnection
) : KeyInputTarget {

    override fun commitText(text: String) {
        inputConnection.commitText(text, 1)
    }

    override fun deleteBackward() {
        val selectedBefore =
            inputConnection.getSelectedText(0)

        if (!selectedBefore.isNullOrEmpty()) {
            inputConnection.commitText("", 1)
            return
        }

        val before =
            inputConnection.getTextBeforeCursor(2, 0)

        if (!before.isNullOrEmpty()) {
            val codePoint =
                Character.codePointBefore(
                    before,
                    before.length
                )

            inputConnection.deleteSurroundingTextInCodePoints(
                1,
                0
            )
        }
    }

    override fun sendEnter() {
        sendKey(KeyEvent.KEYCODE_ENTER)
    }

    override fun moveCursorLeft() {
        sendKey(KeyEvent.KEYCODE_DPAD_LEFT)
    }

    override fun moveCursorRight() {
        sendKey(KeyEvent.KEYCODE_DPAD_RIGHT)
    }

    override fun moveCursorUp() {
        sendKey(KeyEvent.KEYCODE_DPAD_UP)
    }

    override fun moveCursorDown() {
        sendKey(KeyEvent.KEYCODE_DPAD_DOWN)
    }

    override fun selectCurrentWord() {
        val extracted =
            inputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ) ?: return

        val text =
            extracted.text?.toString() ?: return

        val cursor =
            extracted.selectionStart

        if (cursor < 0 || cursor > text.length) {
            return
        }

        if (text.isEmpty()) {
            return
        }

        var start = cursor
        var end = cursor

        while (
            start > 0 &&
            !text[start - 1].isWhitespace()
        ) {
            start--
        }

        while (
            end < text.length &&
            !text[end].isWhitespace()
        ) {
            end++
        }

        if (start != end) {
            inputConnection.setSelection(
                start,
                end
            )
        }
    }

    override fun selectAll() {
        inputConnection.performContextMenuAction(
            android.R.id.selectAll
        )
    }

    override fun cut() {
        inputConnection.performContextMenuAction(
            android.R.id.cut
        )
    }

    override fun copy() {
        inputConnection.performContextMenuAction(
            android.R.id.copy
        )
    }

    override fun paste() {
        inputConnection.performContextMenuAction(
            android.R.id.paste
        )
    }

    override fun undo() {
        inputConnection.performContextMenuAction(
            android.R.id.undo
        )
    }

    override fun redo() {
        inputConnection.performContextMenuAction(
            android.R.id.redo
        )
    }

    override fun moveToBeginning() {
        val extracted =
            inputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ) ?: return

        inputConnection.setSelection(0, 0)
    }

    override fun moveToEnd() {
        val extracted =
            inputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ) ?: return

        val length =
            extracted.text?.length ?: return

        inputConnection.setSelection(
            length,
            length
        )
    }

    private fun sendKey(keyCode: Int) {
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
