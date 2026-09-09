package com.goovat.gvtboard.keyboard.view

class KeyboardLongPressRepeater(
    private val policy: KeyboardLongPressRepeatPolicy =
        KeyboardLongPressRepeatPolicy()
) {

    private var startedAtMs: Long? = null
    private var lastRepeatAtMs: Long? = null

    fun start(timestampMs: Long) {
        startedAtMs = timestampMs
        lastRepeatAtMs = null
    }

    fun shouldRepeat(timestampMs: Long): Boolean {
        val startedAt = startedAtMs ?: return false

        if (timestampMs - startedAt < policy.initialDelayMs) {
            return false
        }

        val lastRepeat = lastRepeatAtMs

        if (lastRepeat == null) {
            lastRepeatAtMs = timestampMs
            return true
        }

        if (timestampMs - lastRepeat >= policy.repeatIntervalMs) {
            lastRepeatAtMs = timestampMs
            return true
        }

        return false
    }

    fun stop() {
        startedAtMs = null
        lastRepeatAtMs = null
    }

    fun isActive(): Boolean =
        startedAtMs != null
}
