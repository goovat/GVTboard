package com.goovat.gvtboard.keyboard.view

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardLongPressRepeatPolicyTest {

    @Test
    fun defaults_areCorrect() {
        val policy = KeyboardLongPressRepeatPolicy()

        assertEquals(500L, policy.initialDelayMs)
        assertEquals(50L, policy.repeatIntervalMs)
    }

    @Test
    fun customValues_arePreserved() {
        val policy = KeyboardLongPressRepeatPolicy(
            initialDelayMs = 700L,
            repeatIntervalMs = 75L
        )

        assertEquals(700L, policy.initialDelayMs)
        assertEquals(75L, policy.repeatIntervalMs)
    }

    @Test(expected = IllegalArgumentException::class)
    fun zeroInitialDelay_isRejected() {
        KeyboardLongPressRepeatPolicy(
            initialDelayMs = 0L
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeInitialDelay_isRejected() {
        KeyboardLongPressRepeatPolicy(
            initialDelayMs = -1L
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun zeroRepeatInterval_isRejected() {
        KeyboardLongPressRepeatPolicy(
            repeatIntervalMs = 0L
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeRepeatInterval_isRejected() {
        KeyboardLongPressRepeatPolicy(
            repeatIntervalMs = -1L
        )
    }
}
