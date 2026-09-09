package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardKeySizingPolicy
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardKeySizingPolicyTest {

    private val policy = KeyboardKeySizingPolicy()

    @Test
    fun normalTextKeyUsesStandardWeight() {
        val key = KeyDefinition(
            label = "Q",
            action = KeyAction.InsertText("Q")
        )

        assertEquals(
            1.0f,
            policy.weight(key),
            0.0f
        )
    }

    @Test
    fun spaceUsesWideWeight() {
        val key = KeyDefinition(
            label = "Space",
            action = KeyAction.Space
        )

        assertEquals(
            5.0f,
            policy.weight(key),
            0.0f
        )
    }

    @Test
    fun shiftUsesWideWeight() {
        val key = KeyDefinition(
            label = "Shift",
            action = KeyAction.Shift
        )

        assertEquals(
            1.25f,
            policy.weight(key),
            0.0f
        )
    }

    @Test
    fun backspaceUsesWideWeight() {
        val key = KeyDefinition(
            label = "Backspace",
            action = KeyAction.Backspace
        )

        assertEquals(
            1.25f,
            policy.weight(key),
            0.0f
        )
    }

    @Test
    fun enterUsesWideWeight() {
        val key = KeyDefinition(
            label = "Enter",
            action = KeyAction.Enter
        )

        assertEquals(
            1.25f,
            policy.weight(key),
            0.0f
        )
    }

    @Test
    fun symbolsUsesWideWeight() {
        val key = KeyDefinition(
            label = "?123",
            action = KeyAction.Symbols
        )

        assertEquals(
            1.25f,
            policy.weight(key),
            0.0f
        )
    }
}
