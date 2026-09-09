package com.goovat.gvtboard.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import com.goovat.gvtboard.keyboard.view.KeyboardView
import com.goovat.gvtboard.keyboard.view.KeyboardLongPressHandler

class GVTboardInputMethodService : InputMethodService() {

    private val keyboardController = KeyboardController()

    private val inputLifecycle =
        KeyboardInputLifecycle(keyboardController)

    private var keyboardView: KeyboardView? = null

    private val longPressHandler =
        KeyboardLongPressHandler { action ->
            dispatchKeyAction(action)
        }

    override fun onStartInput(
        attribute: android.view.inputmethod.EditorInfo?,
        restarting: Boolean
    ) {
        super.onStartInput(attribute, restarting)

        inputLifecycle.startInput()
        keyboardView?.render()
    }

    override fun onFinishInput() {
        inputLifecycle.finishInput()

        keyboardView?.render()

        super.onFinishInput()
    }

    override fun onCreateInputView(): View {
        return KeyboardView(
            context = this,
            controller = keyboardController,
            onKeyAction = { keyDefinition ->
                val result = dispatchKeyAction(keyDefinition.action)

                if (
                    result?.action == KeyAction.Shift ||
                    result?.action == KeyAction.CapsLock ||
                    result?.action == KeyAction.Symbols
                ) {
                    keyboardView?.render()
                }
            },
            onLongPress = { keyDefinition ->
                longPressHandler.handle(keyDefinition)
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
