package com.goovat.gvtboard.keyboard

class KeyboardLayoutSelectorTest {

    private val selector = KeyboardLayoutSelector()

    @org.junit.Test
    fun unshiftedAlphabeticStateSelectsAlphabeticLayout() {
        val state = KeyboardState(
            isShifted = false,
            isSymbols = false
        )

        val rows = selector.select(state)

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic().rows,
            rows
        )
    }

    @org.junit.Test
    fun shiftedAlphabeticStateStillSelectsAlphabeticLayout() {
        val state = KeyboardState(
            isShifted = true,
            isSymbols = false
        )

        val rows = selector.select(state)

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic(isShifted = true).rows,
            rows
        )
    }

    @org.junit.Test
    fun symbolStateSelectsSymbolLayout() {
        val state = KeyboardState(
            isShifted = false,
            isSymbols = true
        )

        val rows = selector.select(state)

        org.junit.Assert.assertEquals(
            SymbolLayout.standard().rows,
            rows
        )
    }

    @org.junit.Test
    fun shiftedSymbolStateStillSelectsSymbolLayout() {
        val state = KeyboardState(
            isShifted = true,
            isSymbols = true
        )

        val rows = selector.select(state)

        org.junit.Assert.assertEquals(
            SymbolLayout.standard().rows,
            rows
        )
    }

    @org.junit.Test
    fun alphabeticAndSymbolLayoutsAreDifferent() {
        val alphabeticRows = selector.select(
            KeyboardState(isSymbols = false)
        )

        val symbolRows = selector.select(
            KeyboardState(isSymbols = true)
        )

        org.junit.Assert.assertNotEquals(
            alphabeticRows,
            symbolRows
        )
    }

    @org.junit.Test
    fun selectingLayoutDoesNotModifyKeyboardState() {
        val state = KeyboardState(
            isShifted = true,
            isSymbols = true
        )

        selector.select(state)

        org.junit.Assert.assertTrue(state.isShifted)
        org.junit.Assert.assertTrue(state.isSymbols)
    }
}
