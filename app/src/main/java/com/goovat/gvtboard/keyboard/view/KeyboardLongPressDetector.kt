package com.goovat.gvtboard.keyboard.view

class KeyboardLongPressDetector(
    private val policy: KeyboardLongPressPolicy =
        KeyboardLongPressPolicy()
) {

    private var pressStartedAtMs: Long? = null
    private var longPressTriggered = false

    fun pressDown(timestampMs: Long) {
        pressStartedAtMs = timestampMs
        longPressTriggered = false
    }

    fun check(timestampMs: Long): Boolean {
        val startedAt = pressStartedAtMs ?: return false

        if (longPressTriggered) {
            return false
        }

        if (timestampMs - startedAt >= policy.durationMs) {
            longPressTriggered = true
            return true
        }

        return false
    }

    fun release() {
        pressStartedAtMs = null
        longPressTriggered = false
    }

    fun cancel() {
        release()
    }

    fun isPressed(): Boolean =
        pressStartedAtMs != null
}
