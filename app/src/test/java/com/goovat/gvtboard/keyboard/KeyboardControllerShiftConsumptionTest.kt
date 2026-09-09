package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardControllerShiftConsumptionTest {

    @Test
    fun shiftedLetter_consumesShift() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)

        assertTrue(controller.state.isShifted)

        val result =
            controller.handle(KeyAction.InsertText("A"))

        assertFalse(result.state.isShifted)
        assertFalse(controller.state.isShifted)
    }

    @Test
    fun unshiftedLetter_doesNotChangeShiftState() {
        val controller = KeyboardController()

        controller.handle(KeyAction.InsertText("a"))

        assertFalse(controller.state.isShifted)
    }

    @Test
    fun shift_remainsActiveAfterSpace() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Space)

        assertTrue(controller.state.isShifted)
    }

    @Test
    fun shift_remainsActiveAfterBackspace() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Backspace)

        assertTrue(controller.state.isShifted)
    }
}
