package com.goovat.gvtboard.keyboard

class KeyEventHandlerTest {

    private val handler = KeyEventHandler()

    @org.junit.Test
    fun insertTextPreservesKeyboardState() {
        val state = KeyboardState()

        val result = handler.handle(
            KeyAction.InsertText("hello"),
            state
        )

        org.junit.Assert.assertEquals(state, result.state)
        org.junit.Assert.assertEquals(
            KeyAction.InsertText("hello"),
            result.action
        )
    }

    @org.junit.Test
    fun backspacePreservesKeyboardState() {
        val state = KeyboardState()

        val result = handler.handle(
            KeyAction.Backspace,
            state
        )

        org.junit.Assert.assertEquals(state, result.state)
        org.junit.Assert.assertEquals(KeyAction.Backspace, result.action)
    }

    @org.junit.Test
    fun enterPreservesKeyboardState() {
        val state = KeyboardState()

        val result = handler.handle(
            KeyAction.Enter,
            state
        )

        org.junit.Assert.assertEquals(state, result.state)
        org.junit.Assert.assertEquals(KeyAction.Enter, result.action)
    }

    @org.junit.Test
    fun spacePreservesKeyboardState() {
        val state = KeyboardState()

        val result = handler.handle(
            KeyAction.Space,
            state
        )

        org.junit.Assert.assertEquals(state, result.state)
        org.junit.Assert.assertEquals(KeyAction.Space, result.action)
    }

    @org.junit.Test
    fun shiftTogglesShiftState() {
        val state = KeyboardState()

        val result = handler.handle(
            KeyAction.Shift,
            state
        )

        org.junit.Assert.assertTrue(result.state.isShifted)
        org.junit.Assert.assertEquals(KeyAction.Shift, result.action)
    }

    @org.junit.Test
    fun symbolsTogglesSymbolState() {
        val state = KeyboardState()

        val result = handler.handle(
            KeyAction.Symbols,
            state
        )

        org.junit.Assert.assertTrue(result.state.isSymbols)
        org.junit.Assert.assertEquals(KeyAction.Symbols, result.action)
    }

    @org.junit.Test
    fun shiftDoesNotChangeSymbolState() {
        val state = KeyboardState(isSymbols = true)

        val result = handler.handle(
            KeyAction.Shift,
            state
        )

        org.junit.Assert.assertTrue(result.state.isShifted)
        org.junit.Assert.assertTrue(result.state.isSymbols)
    }

    @org.junit.Test
    fun symbolsDoesNotChangeShiftState() {
        val state = KeyboardState(isShifted = true)

        val result = handler.handle(
            KeyAction.Symbols,
            state
        )

        org.junit.Assert.assertTrue(result.state.isShifted)
        org.junit.Assert.assertTrue(result.state.isSymbols)
    }
}
