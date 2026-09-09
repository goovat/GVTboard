package com.goovat.gvtboard.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import com.goovat.gvtboard.keyboard.view.KeyboardView

class GVTboardInputMethodService : InputMethodService() {

    private val keyboardController = KeyboardController()

    private var keyboardView: KeyboardView? = null

    override fun onCreateInputView(): View {
        return KeyboardView(
            context = this,
            controller = keyboardController,
            onKeyAction = { keyDefinition ->
                val result = dispatchKeyAction(keyDefinition.action)

                if (
                    result?.action == KeyAction.Shift ||
                    result?.action == KeyAction.Symbols
                ) {
                    keyboardView?.render()
                }
            }
        ).also {
            keyboardView = it
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
