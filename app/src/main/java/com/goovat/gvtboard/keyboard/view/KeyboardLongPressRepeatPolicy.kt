package com.goovat.gvtboard.keyboard.view

class KeyboardLongPressRepeatPolicy(
    val initialDelayMs: Long = DEFAULT_INITIAL_DELAY_MS,
    val repeatIntervalMs: Long = DEFAULT_REPEAT_INTERVAL_MS
) {

    init {
        require(initialDelayMs > 0) {
            "Initial delay must be greater than zero"
        }

        require(repeatIntervalMs > 0) {
            "Repeat interval must be greater than zero"
        }
    }

    companion object {
        const val DEFAULT_INITIAL_DELAY_MS = 500L
        const val DEFAULT_REPEAT_INTERVAL_MS = 50L
    }
}
