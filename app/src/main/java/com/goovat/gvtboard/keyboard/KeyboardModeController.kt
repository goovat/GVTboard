package com.goovat.gvtboard.keyboard

class KeyboardModeController {

    var mode: KeyboardMode = KeyboardMode.Keyboard
        private set

    fun enterEditingMode() {
        mode = KeyboardMode.Editing
    }

    fun exitEditingMode() {
        mode = KeyboardMode.Keyboard
    }

    fun toggleEditingMode() {
        mode = when (mode) {
            KeyboardMode.Keyboard -> KeyboardMode.Editing
            KeyboardMode.Editing -> KeyboardMode.Keyboard
        }
    }

    fun reset() {
        mode = KeyboardMode.Keyboard
    }
}
