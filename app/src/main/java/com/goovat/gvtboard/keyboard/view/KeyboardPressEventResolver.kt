package com.goovat.gvtboard.keyboard.view

class KeyboardPressEventResolver(
    private val detector: KeyboardLongPressDetector =
        KeyboardLongPressDetector()
) {

    fun pressDown(timestampMs: Long) {
        detector.pressDown(timestampMs)
    }

    fun checkLongPress(timestampMs: Long): KeyboardPressEvent? =
        if (detector.check(timestampMs)) {
            KeyboardPressEvent.LongPress
        } else {
            null
        }

    fun release(timestampMs: Long): KeyboardPressEvent {
        val longPressDetected = detector.check(timestampMs)

        detector.release()

        return if (longPressDetected) {
            KeyboardPressEvent.LongPress
        } else {
            KeyboardPressEvent.Tap
        }
    }

    fun cancel() {
        detector.cancel()
    }
}
