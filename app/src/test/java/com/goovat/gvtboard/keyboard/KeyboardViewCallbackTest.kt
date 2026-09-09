package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardKeyInteraction
import com.goovat.gvtboard.keyboard.view.KeyboardPressEvent
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardViewCallbackTest {

    @Test
    fun longPressEventCanBeForwardedToKeyboardCallback() {
        var receivedLabel = ""

        val interaction = KeyboardKeyInteraction(
            onTap = {},
            onLongPress = {
                receivedLabel = it.label
            }
        )

        interaction.handle(
            KeyboardPressEvent.LongPress,
            KeyDefinition(
                label = "A",
                action = KeyAction.InsertText("a")
            )
        )

        assertEquals(
            "A",
            receivedLabel
        )
    }

    @Test
    fun tapAndLongPressRemainSeparateCallbacks() {
        var tapCount = 0
        var longPressCount = 0

        val interaction = KeyboardKeyInteraction(
            onTap = { tapCount++ },
            onLongPress = { longPressCount++ }
        )

        val key = KeyDefinition(
            label = "B",
            action = KeyAction.InsertText("b")
        )

        interaction.handle(
            KeyboardPressEvent.Tap,
            key
        )

        interaction.handle(
            KeyboardPressEvent.LongPress,
            key
        )

        assertEquals(1, tapCount)
        assertEquals(1, longPressCount)
    }
}
