package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardLayoutSelectorCapsLockTest {

    @Test
    fun selectorUsesUppercaseAlphabeticLayoutWhenCapsLockIsEnabled() {
        val state = KeyboardState(isCapsLocked = true)

        val rows = KeyboardLayoutSelector().select(state)

        assertEquals(
            "Q",
            rows[0].keys.first().label
        )

        assertEquals(
            "A",
            rows[1].keys.first().label
        )
    }

    @Test
    fun selectorUsesLowercaseAlphabeticLayoutWhenCapsLockIsDisabled() {
        val state = KeyboardState(isCapsLocked = false)

        val rows = KeyboardLayoutSelector().select(state)

        assertEquals(
            "q",
            rows[0].keys.first().label
        )
    }
}
