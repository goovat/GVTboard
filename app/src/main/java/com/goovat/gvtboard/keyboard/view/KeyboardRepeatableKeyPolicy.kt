package com.goovat.gvtboard.keyboard.view

import com.goovat.gvtboard.keyboard.KeyAction
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardRepeatableKeyPolicy {

    fun isRepeatable(key: KeyDefinition): Boolean =
        key.action == KeyAction.Backspace
}
