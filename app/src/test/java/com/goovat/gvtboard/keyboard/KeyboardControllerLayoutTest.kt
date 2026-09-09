package com.goovat.gvtboard.keyboard

class KeyboardControllerLayoutTest {

    @org.junit.Test
    fun controllerStartsWithAlphabeticLayout() {
        val controller = KeyboardController()

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic().rows,
            controller.currentLayout()
        )
    }

    @org.junit.Test
    fun symbolsActionChangesCurrentLayoutToSymbols() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Symbols)

        org.junit.Assert.assertEquals(
            SymbolLayout.standard().rows,
            controller.currentLayout()
        )
    }

    @org.junit.Test
    fun togglingSymbolsReturnsToAlphabeticLayout() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Symbols)
        controller.handle(KeyAction.Symbols)

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic().rows,
            controller.currentLayout()
        )
    }

    @org.junit.Test
    fun shiftChangesActiveLayoutToUppercase() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic(isShifted = true).rows,
            controller.currentLayout()
        )
    }

    @org.junit.Test
    fun shiftAndSymbolsCanBeActiveTogether() {
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
    fun resetRestoresAlphabeticLayout() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Symbols)
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

    @org.junit.Test
    fun textActionsDoNotChangeCurrentLayout() {
        val controller = KeyboardController()

        val before = controller.currentLayout()

        controller.handle(KeyAction.InsertText("hello"))
        controller.handle(KeyAction.Space)
        controller.handle(KeyAction.Enter)
        controller.handle(KeyAction.Backspace)

        org.junit.Assert.assertEquals(
            before,
            controller.currentLayout()
        )
    }
}
