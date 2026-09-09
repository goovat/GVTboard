package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardKeyInteraction(
    private val onTap: (KeyDefinition) -> Unit,
    private val onLongPress: (KeyDefinition) -> Unit
) {

    fun handle(event: KeyboardPressEvent, key: KeyDefinition) {
        when (event) {
            KeyboardPressEvent.Tap -> onTap(key)
            KeyboardPressEvent.LongPress -> onLongPress(key)
        }
    }
}
