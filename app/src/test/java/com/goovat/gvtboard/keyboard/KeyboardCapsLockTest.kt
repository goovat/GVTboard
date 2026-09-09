package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardCapsLockTest {

    @Test
    fun defaultState_capsLockIsDisabled() {
        val state = KeyboardState()

        assertFalse(state.isCapsLocked)
    }

    @Test
    fun toggleCapsLock_enablesCapsLock() {
        val state = KeyboardState()

        assertTrue(state.toggleCapsLock().isCapsLocked)
    }

    @Test
    fun toggleCapsLock_twiceDisablesCapsLock() {
        val state = KeyboardState()

        val toggled =
            state
                .toggleCapsLock()
                .toggleCapsLock()

        assertFalse(toggled.isCapsLocked)
    }

    @Test
    fun enableCapsLock_setsEnabled() {
        assertTrue(
            KeyboardState()
                .enableCapsLock()
                .isCapsLocked
        )
    }

    @Test
    fun disableCapsLock_setsDisabled() {
        assertFalse(
            KeyboardState(isCapsLocked = true)
                .disableCapsLock()
                .isCapsLocked
        )
    }

    @Test
    fun handlerTogglesCapsLock() {
        val handler = KeyEventHandler()

        val result =
            handler.handle(
                KeyAction.CapsLock,
                KeyboardState()
            )

        assertTrue(result.state.isCapsLocked)
        assertFalse(result.state.isShifted)
    }
}
