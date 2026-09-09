package com.goovat.gvtboard.keyboard

class KeyboardLayoutSelectorShiftTest {

    private val selector = KeyboardLayoutSelector()

    @org.junit.Test
    fun unshiftedStateSelectsLowercaseAlphabeticLayout() {
        val rows = selector.select(
            KeyboardState(
                isShifted = false,
                isSymbols = false
            )
        )

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic().rows,
            rows
        )
    }

    @org.junit.Test
    fun shiftedStateSelectsUppercaseAlphabeticLayout() {
        val rows = selector.select(
            KeyboardState(
                isShifted = true,
                isSymbols = false
            )
        )

        org.junit.Assert.assertEquals(
            KeyboardLayout.alphabetic(isShifted = true).rows,
            rows
        )
    }

    @org.junit.Test
    fun shiftedAlphabeticLayoutContainsUppercaseLetters() {
        val rows = selector.select(
            KeyboardState(
                isShifted = true,
                isSymbols = false
            )
        )

        val letters = rows
            .flatMap { it.keys }
            .filter { it.action is KeyAction.InsertText }
            .map { it.label }

        org.junit.Assert.assertEquals(
            listOf(
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L",
                "Z", "X", "C", "V", "B", "N", "M"
            ),
            letters
        )
    }

    @org.junit.Test
    fun shiftDoesNotAffectSymbolLayout() {
        val unshiftedSymbols = selector.select(
            KeyboardState(
                isShifted = false,
                isSymbols = true
            )
        )

        val shiftedSymbols = selector.select(
            KeyboardState(
                isShifted = true,
                isSymbols = true
            )
        )

        org.junit.Assert.assertEquals(
            unshiftedSymbols,
            shiftedSymbols
        )

        org.junit.Assert.assertEquals(
            SymbolLayout.standard().rows,
            shiftedSymbols
        )
    }

    @org.junit.Test
    fun selectorDoesNotModifyState() {
        val state = KeyboardState(
            isShifted = true,
            isSymbols = false
        )

        selector.select(state)

        org.junit.Assert.assertTrue(state.isShifted)
        org.junit.Assert.assertFalse(state.isSymbols)
    }
}
