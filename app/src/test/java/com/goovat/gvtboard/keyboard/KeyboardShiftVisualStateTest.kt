package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardShiftVisualStateTest {

    @Test
    fun shiftKeyIsActiveWhenShiftIsEnabled() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)

        val shiftKey =
            controller.currentLayout()
                .flatMap { it.keys }
                .first { it.action == KeyAction.Shift }

        assertTrue(controller.state.isShifted)
        assertTrue(shiftKey.action == KeyAction.Shift)
    }

    @Test
    fun shiftKeyIsNotActiveWhenShiftIsDisabled() {
        val controller = KeyboardController()

        val shiftKey =
            controller.currentLayout()
                .flatMap { it.keys }
                .first { it.action == KeyAction.Shift }

        assertFalse(controller.state.isShifted)
        assertTrue(shiftKey.action == KeyAction.Shift)
    }
}
