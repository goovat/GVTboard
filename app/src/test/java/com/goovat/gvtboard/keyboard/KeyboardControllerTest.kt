package com.goovat.gvtboard.keyboard

class KeyboardControllerTest {

    @org.junit.Test
    fun controllerStartsWithDefaultState() {
        val controller = KeyboardController()

        org.junit.Assert.assertEquals(
            KeyboardState(),
            controller.state
        )
    }

    @org.junit.Test
    fun controllerUpdatesStateAfterShift() {
        val controller = KeyboardController()

        val result = controller.handle(KeyAction.Shift)

        org.junit.Assert.assertTrue(controller.state.isShifted)
        org.junit.Assert.assertTrue(result.state.isShifted)
        org.junit.Assert.assertEquals(
            KeyAction.Shift,
            result.action
        )
    }

    @org.junit.Test
    fun controllerUpdatesStateAfterSymbols() {
        val controller = KeyboardController()

        val result = controller.handle(KeyAction.Symbols)

        org.junit.Assert.assertTrue(controller.state.isSymbols)
        org.junit.Assert.assertTrue(result.state.isSymbols)
        org.junit.Assert.assertEquals(
            KeyAction.Symbols,
            result.action
        )
    }

    @org.junit.Test
    fun controllerPreservesStateForInsertText() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)

        val result = controller.handle(
            KeyAction.InsertText("A")
        )

        org.junit.Assert.assertFalse(controller.state.isShifted)
        org.junit.Assert.assertEquals(
            KeyAction.InsertText("A"),
            result.action
        )
    }

    @org.junit.Test
    fun controllerCanToggleShiftRepeatedly() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)
        org.junit.Assert.assertTrue(controller.state.isShifted)

        controller.handle(KeyAction.Shift)
        org.junit.Assert.assertFalse(controller.state.isShifted)
    }

    @org.junit.Test
    fun controllerCanToggleSymbolsRepeatedly() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Symbols)
        org.junit.Assert.assertTrue(controller.state.isSymbols)

        controller.handle(KeyAction.Symbols)
        org.junit.Assert.assertFalse(controller.state.isSymbols)
    }

    @org.junit.Test
    fun resetRestoresDefaultState() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Symbols)

        controller.reset()

        org.junit.Assert.assertEquals(
            KeyboardState(),
            controller.state
        )
    }

    @org.junit.Test
    fun shiftAndSymbolsCanBeActiveTogether() {
        val controller = KeyboardController()

        controller.handle(KeyAction.Shift)
        controller.handle(KeyAction.Symbols)

        org.junit.Assert.assertTrue(controller.state.isShifted)
        org.junit.Assert.assertTrue(controller.state.isSymbols)
    }
}
