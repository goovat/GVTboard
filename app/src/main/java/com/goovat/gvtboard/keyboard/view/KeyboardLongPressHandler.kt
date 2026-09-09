package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.KeyAction
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardLongPressHandler(
    private val resolver: KeyboardLongPressActionResolver =
        KeyboardLongPressActionResolver(),
    private val onAction: (KeyAction) -> Unit
) {

    fun handle(key: KeyDefinition) {
        val action = resolver.resolve(key) ?: return
        onAction(action)
    }
}
