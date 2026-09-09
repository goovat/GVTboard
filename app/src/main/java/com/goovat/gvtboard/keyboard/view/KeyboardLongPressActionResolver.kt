package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.KeyAction
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardLongPressActionResolver {

    fun resolve(key: KeyDefinition): KeyAction? =
        when (key.action) {
            KeyAction.Backspace -> KeyAction.Backspace
            else -> null
        }
}
