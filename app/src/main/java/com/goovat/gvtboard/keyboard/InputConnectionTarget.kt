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
                before.codePointBefore(before.length)

            val charCount =
                Character.charCount(codePoint)

            inputConnection.deleteSurroundingText(
                charCount,
                0
            )
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
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_DPAD_LEFT
            )
        )
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_UP,
                KeyEvent.KEYCODE_DPAD_LEFT
            )
        )
    }

    override fun moveCursorRight() {
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_DPAD_RIGHT
            )
        )
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_UP,
                KeyEvent.KEYCODE_DPAD_RIGHT
            )
        )
    }

    override fun moveCursorUp() {
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_DPAD_UP
            )
        )
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_UP,
                KeyEvent.KEYCODE_DPAD_UP
            )
        )
    }

    override fun moveCursorDown() {
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_DPAD_DOWN
            )
        )
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_UP,
                KeyEvent.KEYCODE_DPAD_DOWN
            )
        )
    }

    override fun selectCurrentWord() {
        val before =
            inputConnection.getTextBeforeCursor(200, 0)
                ?.toString()
                ?: return

        val after =
            inputConnection.getTextAfterCursor(200, 0)
                ?.toString()
                ?: return

        val beforeBoundary =
            before.indexOfLast { it.isWhitespace() } + 1

        val afterBoundary =
            after.indexOfFirst { it.isWhitespace() }
                .let { if (it == -1) after.length else it }

        val start =
            before.length - beforeBoundary

        val end =
            before.length + afterBoundary

        inputConnection.setSelection(
            start,
            end
        )
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
        val before =
            inputConnection.getTextBeforeCursor(
                Int.MAX_VALUE,
                0
            )?.toString() ?: return

        inputConnection.setSelection(
            0,
            0
        )
    }

    override fun moveToEnd() {
        val before =
            inputConnection.getTextBeforeCursor(
                Int.MAX_VALUE,
                0
            )?.toString() ?: return

        val after =
            inputConnection.getTextAfterCursor(
                Int.MAX_VALUE,
                0
            )?.toString() ?: return

        val position =
            before.length + after.length

        inputConnection.setSelection(
            position,
            position
        )
    }
}
