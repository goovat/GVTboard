package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardLongPressActionResolver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class KeyboardLongPressActionResolverTest {

    private val resolver = KeyboardLongPressActionResolver()

    @Test
    fun backspaceResolvesToBackspaceAction() {
        val key = KeyDefinition(
            label = "Backspace",
            action = KeyAction.Backspace
        )

        assertEquals(
            KeyAction.Backspace,
            resolver.resolve(key)
        )
    }

    @Test
    fun insertTextDoesNotHaveLongPressActionYet() {
        val key = KeyDefinition(
            label = "a",
            action = KeyAction.InsertText("a")
        )

        assertNull(
            resolver.resolve(key)
        )
    }

    @Test
    fun spaceDoesNotHaveLongPressActionYet() {
        val key = KeyDefinition(
            label = "Space",
            action = KeyAction.Space
        )

        assertNull(
            resolver.resolve(key)
        )
    }

    @Test
    fun enterDoesNotHaveLongPressActionYet() {
        val key = KeyDefinition(
            label = "Enter",
            action = KeyAction.Enter
        )

        assertNull(
            resolver.resolve(key)
        )
    }

    @Test
    fun shiftDoesNotHaveLongPressActionYet() {
        val key = KeyDefinition(
            label = "Shift",
            action = KeyAction.Shift
        )

        assertNull(
            resolver.resolve(key)
        )
    }

    @Test
    fun symbolsDoesNotHaveLongPressActionYet() {
        val key = KeyDefinition(
            label = "?123",
            action = KeyAction.Symbols
        )

        assertNull(
            resolver.resolve(key)
        )
    }
}
