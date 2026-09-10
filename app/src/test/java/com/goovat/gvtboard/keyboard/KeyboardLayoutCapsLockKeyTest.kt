package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardLayoutCapsLockKeyTest {

    @Test
    fun alphabeticLayoutDoesNotContainCapsLockKey() {
        val layout = KeyboardLayout.alphabetic()

        val actions = layout.rows[2].keys.map { it.action }

        assertFalse(actions.contains(KeyAction.CapsLock))
    }

    @Test
    fun alphabeticLayoutKeepsShiftKey() {
        val layout = KeyboardLayout.alphabetic()

        val actions = layout.rows[2].keys.map { it.action }

        assertTrue(actions.contains(KeyAction.Shift))
    }
}
