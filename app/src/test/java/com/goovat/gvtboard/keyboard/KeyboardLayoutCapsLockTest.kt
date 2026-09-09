package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardLayoutCapsLockTest {

    @Test
    fun capsLockUsesUppercaseLetters() {
        val layout = KeyboardLayout.alphabetic(
            isCapsLocked = true
        )

        assertEquals(
            listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            layout.rows[0].keys.map { it.label }
        )

        assertEquals(
            listOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            layout.rows[1].keys.map { it.label }
        )
    }

    @Test
    fun capsLockKeepsLettersUppercaseWhenShiftIsAlsoActive() {
        val layout = KeyboardLayout.alphabetic(
            isShifted = true,
            isCapsLocked = true
        )

        assertEquals(
            "Q",
            layout.rows[0].keys.first().label
        )
    }

    @Test
    fun capsLockOffUsesLowercaseLetters() {
        val layout = KeyboardLayout.alphabetic(
            isCapsLocked = false
        )

        assertEquals(
            listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
            layout.rows[0].keys.map { it.label }
        )
    }
}
