package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardLongPressHandler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyboardLongPressHandlerTest {

    @Test
    fun resolvedActionIsForwarded() {
        var receivedAction: KeyAction? = null

        val handler = KeyboardLongPressHandler(
            onAction = { receivedAction = it }
        )

        handler.handle(
            KeyDefinition(
                label = "Backspace",
                action = KeyAction.Backspace
            )
        )

        assertEquals(
            KeyAction.Backspace,
            receivedAction
        )
    }

    @Test
    fun unsupportedLongPressDoesNotInvokeCallback() {
        var callbackInvoked = false

        val handler = KeyboardLongPressHandler(
            onAction = {
                callbackInvoked = true
            }
        )

        handler.handle(
            KeyDefinition(
                label = "a",
                action = KeyAction.InsertText("a")
            )
        )

        assertTrue(!callbackInvoked)
    }

    @Test
    fun handlerForwardsOnlyResolvedAction() {
        val receivedActions = mutableListOf<KeyAction>()

        val handler = KeyboardLongPressHandler(
            onAction = { receivedActions.add(it) }
        )

        handler.handle(
            KeyDefinition(
                label = "Backspace",
                action = KeyAction.Backspace
            )
        )

        handler.handle(
            KeyDefinition(
                label = "Space",
                action = KeyAction.Space
            )
        )

        assertEquals(
            listOf(KeyAction.Backspace),
            receivedActions
        )
    }
}
