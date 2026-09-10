package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardModeControllerTest {

    @Test
    fun controllerStartsInKeyboardMode() {
        val controller = KeyboardModeController()

        assertEquals(
            KeyboardMode.Keyboard,
            controller.mode
        )
    }

    @Test
    fun enterEditingModeChangesToEditingMode() {
        val controller = KeyboardModeController()

        controller.enterEditingMode()

        assertEquals(
            KeyboardMode.Editing,
            controller.mode
        )
    }

    @Test
    fun exitEditingModeReturnsToKeyboardMode() {
        val controller = KeyboardModeController()

        controller.enterEditingMode()
        controller.exitEditingMode()

        assertEquals(
            KeyboardMode.Keyboard,
            controller.mode
        )
    }

    @Test
    fun toggleEditingModeSwitchesBetweenModes() {
        val controller = KeyboardModeController()

        controller.toggleEditingMode()

        assertEquals(
            KeyboardMode.Editing,
            controller.mode
        )

        controller.toggleEditingMode()

        assertEquals(
            KeyboardMode.Keyboard,
            controller.mode
        )
    }

    @Test
    fun resetReturnsToKeyboardMode() {
        val controller = KeyboardModeController()

        controller.enterEditingMode()
        controller.reset()

        assertEquals(
            KeyboardMode.Keyboard,
            controller.mode
        )
    }
}
