package com.goovat.gvtboard.keyboard

sealed interface KeyAction {

    data class InsertText(val text: String) : KeyAction

    data object Backspace : KeyAction

    data object Enter : KeyAction

    data object Space : KeyAction

    data object Shift : KeyAction

    data object CapsLock : KeyAction

    data object Symbols : KeyAction

    data object CursorLeft : KeyAction

    data object CursorRight : KeyAction

    data object CursorUp : KeyAction

    data object CursorDown : KeyAction
}
