package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardLayoutCapsLockKeyTest {

    @Test
    fun alphabeticLayoutContainsCapsLockKey() {
        val layout = KeyboardLayout.alphabetic()

        val capsLockKey = layout.rows[2].keys.firstOrNull {
            it.action == KeyAction.CapsLock
        }

        assertTrue(capsLockKey != null)
        assertEquals("Caps", capsLockKey?.label)
    }

    @Test
    fun capsLockKeyIsSeparateFromShiftKey() {
        val layout = KeyboardLayout.alphabetic()

        val actions = layout.rows[2].keys.map { it.action }

        assertTrue(actions.contains(KeyAction.Shift))
        assertTrue(actions.contains(KeyAction.CapsLock))
    }
}
