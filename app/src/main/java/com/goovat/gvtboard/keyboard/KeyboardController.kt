package com.goovat.gvtboard.keyboard

class KeyboardController(
    private val eventHandler: KeyEventHandler = KeyEventHandler(),
    private val layoutSelector: KeyboardLayoutSelector = KeyboardLayoutSelector()
) {

    var state: KeyboardState = KeyboardState()
        private set

    fun handle(action: KeyAction): KeyEventResult {
        val result = eventHandler.handle(action, state)
        state = result.state
        return result
    }

    fun currentLayout(): List<KeyboardRow> =
        layoutSelector.select(state)

    fun reset() {
        state = KeyboardState()
    }
}
