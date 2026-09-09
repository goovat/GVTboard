package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardShiftPolicyTest {

    private val policy = KeyboardShiftPolicy()

    @Test
    fun singleLetter_consumesShift() {
        assertTrue(
            policy.shouldConsumeShift(
                KeyAction.InsertText("a")
            )
        )
    }

    @Test
    fun multipleCharacters_doNotConsumeShift() {
        assertFalse(
            policy.shouldConsumeShift(
                KeyAction.InsertText("abc")
            )
        )
    }

    @Test
    fun space_doesNotConsumeShift() {
        assertFalse(
            policy.shouldConsumeShift(KeyAction.Space)
        )
    }

    @Test
    fun backspace_doesNotConsumeShift() {
        assertFalse(
            policy.shouldConsumeShift(KeyAction.Backspace)
        )
    }

    @Test
    fun enter_doesNotConsumeShift() {
        assertFalse(
            policy.shouldConsumeShift(KeyAction.Enter)
        )
    }
}
