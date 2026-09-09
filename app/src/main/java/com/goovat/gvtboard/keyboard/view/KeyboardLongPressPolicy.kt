package com.goovat.gvtboard.keyboard.view

class KeyboardLongPressPolicy(
    val durationMs: Long = DEFAULT_DURATION_MS
) {

    init {
        require(durationMs > 0) {
            "Long-press duration must be greater than zero"
        }
    }

    companion object {
        const val DEFAULT_DURATION_MS = 500L
    }
}
