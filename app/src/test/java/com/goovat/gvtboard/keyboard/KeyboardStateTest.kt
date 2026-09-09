package com.goovat.gvtboard.keyboard

class KeyboardStateTest {

    @org.junit.Test
    fun defaultStateIsAlphabeticAndUnshifted() {
        val state = KeyboardState()

        org.junit.Assert.assertFalse(state.isShifted)
        org.junit.Assert.assertFalse(state.isSymbols)
    }

    @org.junit.Test
    fun toggleShiftChangesShiftState() {
        val state = KeyboardState()

        val shifted = state.toggleShift()
        val unshifted = shifted.toggleShift()

        org.junit.Assert.assertTrue(shifted.isShifted)
        org.junit.Assert.assertFalse(unshifted.isShifted)
    }

    @org.junit.Test
    fun toggleSymbolsChangesSymbolState() {
        val state = KeyboardState()

        val symbols = state.toggleSymbols()
        val alphabetic = symbols.toggleSymbols()

        org.junit.Assert.assertTrue(symbols.isSymbols)
        org.junit.Assert.assertFalse(alphabetic.isSymbols)
    }

    @org.junit.Test
    fun enablingShiftMakesStateShifted() {
        val state = KeyboardState(isShifted = false)

        org.junit.Assert.assertTrue(state.enableShift().isShifted)
    }

    @org.junit.Test
    fun disablingShiftMakesStateUnshifted() {
        val state = KeyboardState(isShifted = true)

        org.junit.Assert.assertFalse(state.disableShift().isShifted)
    }

    @org.junit.Test
    fun enablingSymbolsMakesStateSymbolMode() {
        val state = KeyboardState(isSymbols = false)

        org.junit.Assert.assertTrue(state.enableSymbols().isSymbols)
    }

    @org.junit.Test
    fun disablingSymbolsMakesStateAlphabeticMode() {
        val state = KeyboardState(isSymbols = true)

        org.junit.Assert.assertFalse(state.disableSymbols().isSymbols)
    }
}
