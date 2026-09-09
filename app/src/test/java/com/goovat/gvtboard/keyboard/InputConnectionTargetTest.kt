package com.goovat.gvtboard.keyboard

import android.view.KeyEvent
import android.view.inputmethod.BaseInputConnection
import android.view.inputmethod.InputConnection
import android.view.View

class InputConnectionTargetTest {

    private class FakeInputConnection : BaseInputConnection(
        View(null),
        false
    ) {

        val committedTexts = mutableListOf<String>()
        var codePointDeleteCount = 0
        var fallbackDeleteCount = 0
        val keyEvents = mutableListOf<KeyEvent>()

        override fun commitText(
            text: CharSequence,
            newCursorPosition: Int
        ): Boolean {
            committedTexts += text.toString()
            return true
        }

        override fun deleteSurroundingTextInCodePoints(
            beforeLength: Int,
            afterLength: Int
        ): Boolean {
            codePointDeleteCount++
            return true
        }

        override fun deleteSurroundingText(
            beforeLength: Int,
            afterLength: Int
        ): Boolean {
            fallbackDeleteCount++
            return true
        }

        override fun sendKeyEvent(event: KeyEvent): Boolean {
            keyEvents += event
            return true
        }
    }

    @org.junit.Test
    fun commitTextUsesInputConnection() {
        val inputConnection = FakeInputConnection()
        val target = InputConnectionTarget(inputConnection)

        target.commitText("hello")

        org.junit.Assert.assertEquals(
            listOf("hello"),
            inputConnection.committedTexts
        )
    }

    @org.junit.Test
    fun commitTextPreservesUppercaseText() {
        val inputConnection = FakeInputConnection()
        val target = InputConnectionTarget(inputConnection)

        target.commitText("Q")

        org.junit.Assert.assertEquals(
            listOf("Q"),
            inputConnection.committedTexts
        )
    }

    @org.junit.Test
    fun deleteBackwardUsesCodePointDeletion() {
        val inputConnection = FakeInputConnection()
        val target = InputConnectionTarget(inputConnection)

        target.deleteBackward()

        org.junit.Assert.assertEquals(
            1,
            inputConnection.codePointDeleteCount
        )

        org.junit.Assert.assertEquals(
            0,
            inputConnection.fallbackDeleteCount
        )
    }

    @org.junit.Test
    fun enterSendsDownAndUpEvents() {
        val inputConnection = FakeInputConnection()
        val target = InputConnectionTarget(inputConnection)

        target.sendEnter()

        org.junit.Assert.assertEquals(
            2,
            inputConnection.keyEvents.size
        )

        org.junit.Assert.assertEquals(
            KeyEvent.ACTION_DOWN,
            inputConnection.keyEvents[0].action
        )

        org.junit.Assert.assertEquals(
            KeyEvent.ACTION_UP,
            inputConnection.keyEvents[1].action
        )

        org.junit.Assert.assertEquals(
            KeyEvent.KEYCODE_ENTER,
            inputConnection.keyEvents[0].keyCode
        )

        org.junit.Assert.assertEquals(
            KeyEvent.KEYCODE_ENTER,
            inputConnection.keyEvents[1].keyCode
        )
    }
}
