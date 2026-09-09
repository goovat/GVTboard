package com.goovat.gvtboard.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.TextView

class GVTboardInputMethodService : InputMethodService() {

    private val keyboardController = KeyboardController()

    override fun onCreateInputView(): View {
        return TextView(this).apply {
            text = "GVTboard"
            textSize = 24f
            setPadding(32, 32, 32, 32)
        }
    }

    fun dispatchKeyAction(action: KeyAction): KeyEventResult? {
        val inputConnection = currentInputConnection ?: return null

        val target = InputConnectionTarget(inputConnection)
        val executor = KeyActionExecutor(target)
        val dispatcher = KeyboardActionDispatcher(
            controller = keyboardController,
            executor = executor
        )

        return dispatcher.dispatch(action)
    }
}
