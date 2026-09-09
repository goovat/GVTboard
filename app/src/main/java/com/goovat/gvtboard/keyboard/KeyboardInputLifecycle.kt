package com.goovat.gvtboard.keyboard

class KeyboardInputLifecycle(
    private val controller: KeyboardController
) {

    fun startInput() {
        controller.reset()
    }

    fun finishInput() {
        controller.reset()
    }
}
