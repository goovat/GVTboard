package com.goovat.gvtboard.keyboard

class KeyboardController(
    private val eventHandler: KeyEventHandler = KeyEventHandler()
) {

    var state: KeyboardState = KeyboardState()
        private set

    fun handle(action: KeyAction): KeyEventResult {
        val result = eventHandler.handle(action, state)
        state = result.state
        return result
    }

    fun reset() {
        state = KeyboardState()
    }
}
