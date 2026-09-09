package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardPressEvent
import com.goovat.gvtboard.keyboard.view.KeyboardPressEventResolver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class KeyboardPressEventResolverTest {

    @Test
    fun releaseBeforeThresholdProducesTap() {
        val resolver = KeyboardPressEventResolver()

        resolver.pressDown(1_000L)

        assertEquals(
            KeyboardPressEvent.Tap,
            resolver.release(1_200L)
        )
    }

    @Test
    fun releaseAtThresholdProducesLongPress() {
        val resolver = KeyboardPressEventResolver()

        resolver.pressDown(1_000L)

        assertEquals(
            KeyboardPressEvent.LongPress,
            resolver.release(1_500L)
        )
    }

    @Test
    fun checkLongPressProducesEventAtThreshold() {
        val resolver = KeyboardPressEventResolver()

        resolver.pressDown(1_000L)

        assertNull(
            resolver.checkLongPress(1_499L)
        )

        assertEquals(
            KeyboardPressEvent.LongPress,
            resolver.checkLongPress(1_500L)
        )
    }

    @Test
    fun longPressIsNotRepeated() {
        val resolver = KeyboardPressEventResolver()

        resolver.pressDown(1_000L)

        assertEquals(
            KeyboardPressEvent.LongPress,
            resolver.checkLongPress(1_500L)
        )

        assertNull(
            resolver.checkLongPress(1_600L)
        )
    }

    @Test
    fun cancelReturnsResolverToIdleState() {
        val resolver = KeyboardPressEventResolver()

        resolver.pressDown(1_000L)
        resolver.cancel()

        assertEquals(
            KeyboardPressEvent.Tap,
            resolver.release(2_000L)
        )
    }

    @Test
    fun nextPressStartsNewCycle() {
        val resolver = KeyboardPressEventResolver()

        resolver.pressDown(1_000L)
        assertEquals(
            KeyboardPressEvent.LongPress,
            resolver.release(1_500L)
        )

        resolver.pressDown(2_000L)

        assertEquals(
            KeyboardPressEvent.Tap,
            resolver.release(2_200L)
        )
    }
}
