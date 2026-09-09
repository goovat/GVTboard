package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.KeyAction
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardRepeatableKeyPolicy {

    fun isRepeatable(key: KeyDefinition): Boolean =
        when (key.action) {
            KeyAction.Backspace,
            KeyAction.CursorLeft,
            KeyAction.CursorRight,
            KeyAction.CursorUp,
            KeyAction.CursorDown -> true

            else -> false
        }
}
