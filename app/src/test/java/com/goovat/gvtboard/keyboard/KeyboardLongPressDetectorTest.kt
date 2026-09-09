package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardLongPressDetector
import com.goovat.gvtboard.keyboard.view.KeyboardLongPressPolicy
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardLongPressDetectorTest {

    @Test
    fun pressBeforeDurationDoesNotTrigger() {
        val detector = KeyboardLongPressDetector()

        detector.pressDown(1_000L)

        assertFalse(
            detector.check(1_499L)
        )
    }

    @Test
    fun pressAtDurationTriggers() {
        val detector = KeyboardLongPressDetector()

        detector.pressDown(1_000L)

        assertTrue(
            detector.check(1_500L)
        )
    }

    @Test
    fun pressAfterDurationTriggers() {
        val detector = KeyboardLongPressDetector()

        detector.pressDown(1_000L)

        assertTrue(
            detector.check(1_700L)
        )
    }

    @Test
    fun longPressTriggersOnlyOnce() {
        val detector = KeyboardLongPressDetector()

        detector.pressDown(1_000L)

        assertTrue(detector.check(1_500L))
        assertFalse(detector.check(1_600L))
        assertFalse(detector.check(2_000L))
    }

    @Test
    fun releaseResetsDetector() {
        val detector = KeyboardLongPressDetector()

        detector.pressDown(1_000L)
        detector.release()

        assertFalse(detector.isPressed())
        assertFalse(detector.check(2_000L))
    }

    @Test
    fun cancelResetsDetector() {
        val detector = KeyboardLongPressDetector()

        detector.pressDown(1_000L)
        detector.cancel()

        assertFalse(detector.isPressed())
        assertFalse(detector.check(2_000L))
    }

    @Test
    fun customPolicyIsUsed() {
        val detector = KeyboardLongPressDetector(
            policy = KeyboardLongPressPolicy(
                durationMs = 700L
            )
        )

        detector.pressDown(1_000L)

        assertFalse(detector.check(1_699L))
        assertTrue(detector.check(1_700L))
    }

    @Test
    fun newPressStartsNewDetectionCycle() {
        val detector = KeyboardLongPressDetector()

        detector.pressDown(1_000L)
        assertTrue(detector.check(1_500L))

        detector.release()
        detector.pressDown(2_000L)

        assertFalse(detector.check(2_499L))
        assertTrue(detector.check(2_500L))
    }
}
