package com.goovat.gvtboard.keyboard

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KeyboardStateTest {

    @Test
    fun defaultStateIsAlphabeticAndUnshifted() {
        val state = KeyboardState()

        assertFalse(state.isShifted)
        assertFalse(state.isSymbols)
    }

    @Test
    fun toggleShiftChangesShiftState() {
        val state = KeyboardState()

        val shifted = state.toggleShift()
        val unshifted = shifted.toggleShift()

        assertTrue(shifted.isShifted)
        assertFalse(unshifted.isShifted)
    }

    @Test
    fun toggleSymbolsChangesSymbolState() {
        val state = KeyboardState()

        val symbols = state.toggleSymbols()
        val alphabetic = symbols.toggleSymbols()

        assertTrue(symbols.isSymbols)
        assertFalse(alphabetic.isSymbols)
    }

    @Test
    fun enablingShiftMakesStateShifted() {
        val state = KeyboardState(isShifted = false)

        assertTrue(state.enableShift().isShifted)
    }

    @Test
    fun disablingShiftMakesStateUnshifted() {
        val state = KeyboardState(isShifted = true)

        assertFalse(state.disableShift().isShifted)
    }

    @Test
    fun enablingSymbolsMakesStateSymbolMode() {
        val state = KeyboardState(isSymbols = false)

        assertTrue(state.enableSymbols().isSymbols)
    }

    @Test
    fun disablingSymbolsMakesStateAlphabeticMode() {
        val state = KeyboardState(isSymbols = true)

        assertFalse(state.disableSymbols().isSymbols)
    }
}
