package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardActionDispatcherTest {

    private class FakeInputTarget : KeyInputTarget {

        val committedTexts = mutableListOf<String>()
        var backspaceCount = 0
        var enterCount = 0

        override fun commitText(text: String) {
            committedTexts += text
        }

        override fun deleteBackward() {
            backspaceCount++
        }

        override fun sendEnter() {
            enterCount++
        }
    }

    @Test
    fun insertTextUpdatesControllerAndExecutesText() {
        val target = FakeInputTarget()
        val controller = KeyboardController()
        val executor = KeyActionExecutor(target)
        val dispatcher = KeyboardActionDispatcher(controller, executor)

        val result = dispatcher.dispatch(KeyAction.InsertText("hello"))

        assertEquals(
            KeyAction.InsertText("hello"),
            result.action
        )

        assertEquals(
            listOf("hello"),
            target.committedTexts
        )
    }

    @Test
    fun shiftUpdatesControllerAndDoesNotWriteText() {
        val target = FakeInputTarget()
        val controller = KeyboardController()
        val executor = KeyActionExecutor(target)
        val dispatcher = KeyboardActionDispatcher(controller, executor)

        dispatcher.dispatch(KeyAction.Shift)

        assertTrue(controller.state.isShifted)
        assertTrue(target.committedTexts.isEmpty())
    }

    @Test
    fun symbolsUpdatesControllerAndDoesNotWriteText() {
        val target = FakeInputTarget()
        val controller = KeyboardController()
        val executor = KeyActionExecutor(target)
        val dispatcher = KeyboardActionDispatcher(controller, executor)

        dispatcher.dispatch(KeyAction.Symbols)

        assertTrue(controller.state.isSymbols)
        assertTrue(target.committedTexts.isEmpty())
    }

    @Test
    fun backspaceExecutesAgainstTarget() {
        val target = FakeInputTarget()
        val controller = KeyboardController()
        val executor = KeyActionExecutor(target)
        val dispatcher = KeyboardActionDispatcher(controller, executor)

        dispatcher.dispatch(KeyAction.Backspace)

        assertEquals(1, target.backspaceCount)
    }

    @Test
    fun enterExecutesAgainstTarget() {
        val target = FakeInputTarget()
        val controller = KeyboardController()
        val executor = KeyActionExecutor(target)
        val dispatcher = KeyboardActionDispatcher(controller, executor)

        dispatcher.dispatch(KeyAction.Enter)

        assertEquals(1, target.enterCount)
    }

    @Test
    fun spaceExecutesAgainstTarget() {
        val target = FakeInputTarget()
        val controller = KeyboardController()
        val executor = KeyActionExecutor(target)
        val dispatcher = KeyboardActionDispatcher(controller, executor)

        dispatcher.dispatch(KeyAction.Space)

        assertEquals(
            listOf(" "),
            target.committedTexts
        )
    }
}
