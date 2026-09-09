package com.goovat.gvtboard.keyboard.view

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardLongPressRepeaterTest {

    private val policy = KeyboardLongPressRepeatPolicy(
        initialDelayMs = 500L,
        repeatIntervalMs = 50L
    )

    @Test
    fun beforeInitialDelay_doesNotRepeat() {
        val repeater = KeyboardLongPressRepeater(policy)

        repeater.start(1_000L)

        assertFalse(repeater.shouldRepeat(1_499L))
    }

    @Test
    fun atInitialDelay_repeats() {
        val repeater = KeyboardLongPressRepeater(policy)

        repeater.start(1_000L)

        assertTrue(repeater.shouldRepeat(1_500L))
    }

    @Test
    fun beforeRepeatInterval_doesNotRepeatAgain() {
        val repeater = KeyboardLongPressRepeater(policy)

        repeater.start(1_000L)

        assertTrue(repeater.shouldRepeat(1_500L))
        assertFalse(repeater.shouldRepeat(1_549L))
    }

    @Test
    fun atRepeatInterval_repeatsAgain() {
        val repeater = KeyboardLongPressRepeater(policy)

        repeater.start(1_000L)

        assertTrue(repeater.shouldRepeat(1_500L))
        assertTrue(repeater.shouldRepeat(1_550L))
    }

    @Test
    fun stop_disablesRepeating() {
        val repeater = KeyboardLongPressRepeater(policy)

        repeater.start(1_000L)
        repeater.stop()

        assertFalse(repeater.shouldRepeat(2_000L))
        assertFalse(repeater.isActive())
    }

    @Test
    fun start_makesRepeaterActive() {
        val repeater = KeyboardLongPressRepeater(policy)

        repeater.start(1_000L)

        assertTrue(repeater.isActive())
    }
}
