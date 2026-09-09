package com.goovat.gvtboard.keyboard

class KeyboardLayoutShiftTest {

    @org.junit.Test
    fun defaultAlphabeticLayoutUsesLowercaseLetters() {
        val layout = KeyboardLayout.alphabetic()

        val letters = layout.rows
            .flatMap { it.keys }
            .filter { it.action is KeyAction.InsertText }
            .map { it.label }

        org.junit.Assert.assertEquals(
            listOf(
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
                "a", "s", "d", "f", "g", "h", "j", "k", "l",
                "z", "x", "c", "v", "b", "n", "m"
            ),
            letters
        )
    }

    @org.junit.Test
    fun shiftedAlphabeticLayoutUsesUppercaseLetters() {
        val layout = KeyboardLayout.alphabetic(isShifted = true)

        val letters = layout.rows
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
    fun shiftedLetterActionsInsertUppercaseText() {
        val layout = KeyboardLayout.alphabetic(isShifted = true)

        val letterKeys = layout.rows
            .flatMap { it.keys }
            .filter { it.action is KeyAction.InsertText }

        letterKeys.forEach { key ->
            val action = key.action as KeyAction.InsertText

            org.junit.Assert.assertEquals(
                key.label,
                action.text
            )
        }
    }

    @org.junit.Test
    fun shiftedAndUnshiftedLayoutsHaveSameStructure() {
        val normal = KeyboardLayout.alphabetic()
        val shifted = KeyboardLayout.alphabetic(isShifted = true)

        org.junit.Assert.assertEquals(
            normal.rows.size,
            shifted.rows.size
        )

        normal.rows.zip(shifted.rows).forEach { (normalRow, shiftedRow) ->
            org.junit.Assert.assertEquals(
                normalRow.keys.size,
                shiftedRow.keys.size
            )
        }
    }

    @org.junit.Test
    fun functionKeysRemainUnchangedWhenShifted() {
        val normal = KeyboardLayout.alphabetic()
        val shifted = KeyboardLayout.alphabetic(isShifted = true)

        val normalFunctions = normal.rows
            .flatMap { it.keys }
            .filter { it.action !is KeyAction.InsertText }
            .map { it.label }

        val shiftedFunctions = shifted.rows
            .flatMap { it.keys }
            .filter { it.action !is KeyAction.InsertText }
            .map { it.label }

        org.junit.Assert.assertEquals(
            normalFunctions,
            shiftedFunctions
        )
    }
}
