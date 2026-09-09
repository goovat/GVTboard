package com.goovat.gvtboard.keyboard

class KeyboardController(
    private val eventHandler: KeyEventHandler = KeyEventHandler(),
    private val layoutSelector: KeyboardLayoutSelector = KeyboardLayoutSelector(),
    private val shiftPolicy: KeyboardShiftPolicy = KeyboardShiftPolicy()
) {

    var state: KeyboardState = KeyboardState()
        private set

    fun handle(action: KeyAction): KeyEventResult {
        val result = eventHandler.handle(action, state)

        state = if (shiftPolicy.shouldConsumeShift(action)) {
            result.state.disableShift()
        } else {
            result.state
        }

        return result.copy(state = state)
    }

    fun currentLayout(): List<KeyboardRow> =
        layoutSelector.select(state)

    fun reset() {
        state = KeyboardState()
    }
}
