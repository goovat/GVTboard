package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardLongPressPolicy
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardLongPressPolicyTest {

    @Test
    fun defaultDurationIs500Milliseconds() {
        val policy = KeyboardLongPressPolicy()

        assertEquals(
            500L,
            policy.durationMs
        )
    }

    @Test
    fun durationCanBeCustomized() {
        val policy = KeyboardLongPressPolicy(
            durationMs = 700L
        )

        assertEquals(
            700L,
            policy.durationMs
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun zeroDurationIsRejected() {
        KeyboardLongPressPolicy(
            durationMs = 0L
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeDurationIsRejected() {
        KeyboardLongPressPolicy(
            durationMs = -1L
        )
    }
}
