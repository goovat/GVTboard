package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.KeyAction
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardKeySizingPolicy {

    fun weight(key: KeyDefinition): Float =
        when (key.action) {
            KeyAction.Space -> 5.0f
            KeyAction.Shift -> 1.25f
            KeyAction.CapsLock -> 1.25f
            KeyAction.Backspace -> 1.25f
            KeyAction.Enter -> 1.25f
            KeyAction.Symbols -> 1.25f
            KeyAction.CursorLeft -> 1.25f
            KeyAction.CursorRight -> 1.25f
            KeyAction.CursorUp -> 1.25f
            KeyAction.CursorDown -> 1.25f
            is KeyAction.InsertText -> 1.0f
        }
}
