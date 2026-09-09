package com.goovat.gvtboard.keyboard

data class KeyEventResult(
    val state: KeyboardState,
    val action: KeyAction
)

class KeyEventHandler {

    fun handle(
        action: KeyAction,
        state: KeyboardState
    ): KeyEventResult {
        val nextState = when (action) {
            KeyAction.Shift -> state.toggleShift()
            KeyAction.Symbols -> state.toggleSymbols()
            else -> state
        }

        return KeyEventResult(
            state = nextState,
            action = action
        )
    }
}
