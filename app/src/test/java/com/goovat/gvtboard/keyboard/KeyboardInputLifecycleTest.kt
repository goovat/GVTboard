package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardInputLifecycleTest {

    @Test
    fun startInputResetsKeyboardState() {
        val controller = KeyboardController()
        val lifecycle = KeyboardInputLifecycle(controller)

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Symbols)

        lifecycle.startInput()

        assertFalse(controller.state.isShifted)
        assertFalse(controller.state.isSymbols)
    }

    @Test
    fun finishInputResetsKeyboardState() {
        val controller = KeyboardController()
        val lifecycle = KeyboardInputLifecycle(controller)

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Symbols)

        lifecycle.finishInput()

        assertFalse(controller.state.isShifted)
        assertFalse(controller.state.isSymbols)
    }

    @Test
    fun lifecycleCanBeReusedAcrossInputSessions() {
        val controller = KeyboardController()
        val lifecycle = KeyboardInputLifecycle(controller)

        lifecycle.startInput()

        controller.handle(KeyAction.Shift)
        lifecycle.finishInput()

        lifecycle.startInput()

        assertFalse(controller.state.isShifted)
        assertFalse(controller.state.isSymbols)

        controller.handle(KeyAction.Symbols)
        lifecycle.finishInput()

        assertFalse(controller.state.isShifted)
        assertFalse(controller.state.isSymbols)
    }

    @Test
    fun startInputDoesNotReplaceController() {
        val controller = KeyboardController()
        val lifecycle = KeyboardInputLifecycle(controller)

        controller.handle(KeyAction.Shift)
        lifecycle.startInput()

        assertTrue(controller.currentLayout().isNotEmpty())
    }
}
