package com.goovat.gvtboard.keyboard

class KeyActionExecutor(
    private val inputTarget: KeyInputTarget
) {

    fun execute(action: KeyAction) {
        when (action) {
            is KeyAction.InsertText -> inputTarget.commitText(action.text)
            KeyAction.Space -> inputTarget.commitText(" ")
            KeyAction.Backspace -> inputTarget.deleteBackward()
            KeyAction.Enter -> inputTarget.sendEnter()
            KeyAction.Shift -> Unit
            KeyAction.CapsLock -> Unit
            KeyAction.Symbols -> Unit
            KeyAction.CursorLeft -> inputTarget.moveCursorLeft()
            KeyAction.CursorRight -> inputTarget.moveCursorRight()
            KeyAction.CursorUp -> inputTarget.moveCursorUp()
            KeyAction.CursorDown -> inputTarget.moveCursorDown()
        }
    }
}
