package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardKeyInteraction
import com.goovat.gvtboard.keyboard.view.KeyboardPressEvent
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardKeyInteractionTest {

    @Test
    fun tapInvokesTapCallback() {
        var tapCount = 0
        var longPressCount = 0

        val interaction = KeyboardKeyInteraction(
            onTap = { tapCount++ },
            onLongPress = { longPressCount++ }
        )

        val key = KeyDefinition(
            label = "A",
            action = KeyAction.InsertText("a")
        )

        interaction.handle(
            KeyboardPressEvent.Tap,
            key
        )

        assertEquals(1, tapCount)
        assertEquals(0, longPressCount)
    }

    @Test
    fun longPressInvokesLongPressCallback() {
        var tapCount = 0
        var longPressCount = 0

        val interaction = KeyboardKeyInteraction(
            onTap = { tapCount++ },
            onLongPress = { longPressCount++ }
        )

        val key = KeyDefinition(
            label = "A",
            action = KeyAction.InsertText("a")
        )

        interaction.handle(
            KeyboardPressEvent.LongPress,
            key
        )

        assertEquals(0, tapCount)
        assertEquals(1, longPressCount)
    }

    @Test
    fun correctKeyIsForwarded() {
        var receivedLabel = ""

        val interaction = KeyboardKeyInteraction(
            onTap = { receivedLabel = it.label },
            onLongPress = {}
        )

        val key = KeyDefinition(
            label = "Q",
            action = KeyAction.InsertText("q")
        )

        interaction.handle(
            KeyboardPressEvent.Tap,
            key
        )

        assertEquals("Q", receivedLabel)
    }
}
