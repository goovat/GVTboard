package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardLongPressIntegrationTest {

    @Test
    fun longPressBackspace_resolvesToBackspaceAction() {
        val receivedActions = mutableListOf<KeyAction>()

        val handler = com.goovat.gvtboard.keyboard.view.KeyboardLongPressHandler(
            onAction = { action ->
                receivedActions += action
            }
        )

        handler.handle(
            KeyDefinition(
                label = "Backspace",
                action = KeyAction.Backspace
            )
        )

        assertEquals(
            listOf(KeyAction.Backspace),
            receivedActions
        )
    }
}
