package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.KeyAction
import com.goovat.gvtboard.keyboard.KeyDefinition
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardRepeatableKeyPolicyTest {

    private val policy = KeyboardRepeatableKeyPolicy()

    @Test
    fun backspace_isRepeatable() {
        assertTrue(
            policy.isRepeatable(
                KeyDefinition(
                    label = "Backspace",
                    action = KeyAction.Backspace
                )
            )
        )
    }

    @Test
    fun cursorNavigationKeys_areRepeatable() {
        val cursorKeys = listOf(
            KeyDefinition("←", KeyAction.CursorLeft),
            KeyDefinition("→", KeyAction.CursorRight),
            KeyDefinition("↑", KeyAction.CursorUp),
            KeyDefinition("↓", KeyAction.CursorDown)
        )

        cursorKeys.forEach { key ->
            assertTrue(
                "Expected ${key.label} to be repeatable",
                policy.isRepeatable(key)
            )
        }
    }

    @Test
    fun regularTextKey_isNotRepeatable() {
        assertFalse(
            policy.isRepeatable(
                KeyDefinition(
                    label = "a",
                    action = KeyAction.InsertText("a")
                )
            )
        )
    }

    @Test
    fun enter_isNotRepeatable() {
        assertFalse(
            policy.isRepeatable(
                KeyDefinition(
                    label = "Enter",
                    action = KeyAction.Enter
                )
            )
        )
    }
}
