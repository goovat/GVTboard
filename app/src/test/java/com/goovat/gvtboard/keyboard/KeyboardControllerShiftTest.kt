package com.goovat.gvtboard.keyboard

class KeyboardControllerShiftTest {

    @org.junit.Test
    fun shiftActionChangesCurrentAlphabeticLayoutToUppercase() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)

        org.junit.Assert.assertTrue(controller.state.isShifted)

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic(isShifted = true).rows,
            controller.currentLayout()
        )
    }

    @org.junit.Test
    fun togglingShiftReturnsToLowercaseLayout() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Shift)

        org.junit.Assert.assertFalse(controller.state.isShifted)

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic().rows,
            controller.currentLayout()
        )
    }

    @org.junit.Test
    fun shiftedControllerLettersInsertUppercaseText() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)

        val letterKeys = controller.currentLayout()
            .flatMap { it.keys }
            .filter { it.action is KeyAction.InsertText }

        letterKeys.forEach { key ->
            val action = key.action as KeyAction.InsertText

            org.junit.Assert.assertTrue(
                key.label.all { it.isUpperCase() }
            )

            org.junit.Assert.assertEquals(
                key.label,
                action.text
            )
        }
    }

    @org.junit.Test
    fun shiftAndSymbolsKeepSymbolsActive() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Symbols)

        org.junit.Assert.assertTrue(controller.state.isShifted)
        org.junit.Assert.assertTrue(controller.state.isSymbols)

        org.junit.Assert.assertEquals(
            SymbolLayout.standard().rows,
            controller.currentLayout()
        )
    }

    @org.junit.Test
    fun resetClearsShiftAndRestoresLowercaseLayout() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)

        controller.reset()

        org.junit.Assert.assertEquals(
            KeyboardState(),
            controller.state
        )

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic().rows,
            controller.currentLayout()
        )
    }
}
