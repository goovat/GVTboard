package com.goovat.gvtboard.keyboard

class KeyboardShiftPolicy {

    fun shouldConsumeShift(action: KeyAction): Boolean =
        action is KeyAction.InsertText && action.text.length == 1 &&
            action.text[0].isLetter()
}
