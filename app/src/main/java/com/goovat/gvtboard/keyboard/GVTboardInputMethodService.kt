package com.goovat.gvtboard.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import com.goovat.gvtboard.keyboard.view.KeyboardContainerView
import com.goovat.gvtboard.keyboard.view.KeyboardLongPressHandler
import com.goovat.gvtboard.keyboard.view.KeyboardView
import com.goovat.gvtboard.keyboard.view.SuggestionRowSelectionHandler

class GVTboardInputMethodService : InputMethodService() {

    private val keyboardController = KeyboardController()

    private val inputLifecycle =
        KeyboardInputLifecycle(keyboardController)

    private var keyboardView: KeyboardView? = null

    private var keyboardContainerView:
        KeyboardContainerView? = null

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
        val keyboard =
            KeyboardView(
                context = this,
                controller = keyboardController,
                onKeyAction = { keyDefinition ->
                    val result =
                        dispatchKeyAction(
                            keyDefinition.action
                        )

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
            )

        keyboardView = keyboard

        return KeyboardContainerView(
            context = this,
            keyboardView = keyboard,
            onSuggestionSelected = { suggestion ->
                selectSuggestion(suggestion)
            }
        ).also {
            keyboardContainerView = it
        }
    }

    private fun selectSuggestion(
        suggestion: SuggestionCandidate
    ): Boolean {
        val inputConnection =
            currentInputConnection
                ?: return false

        val selectionTarget =
            InputConnectionSuggestionSelectionTarget(
                inputConnection
            )

        val coordinator =
            SuggestionSelectionCoordinator(
                SuggestionSelectionService(
                    contextService =
                        TextEditingContextService(
                            InputConnectionEditingContextTarget(
                                inputConnection
                            )
                        ),
                    currentWordService =
                        CurrentWordService(
                            TextEditingContextService(
                                InputConnectionEditingContextTarget(
                                    inputConnection
                                )
                            )
                        ),
                    target = selectionTarget
                )
            )

        val handler =
            SuggestionRowSelectionHandler(
                coordinator
            )

        return handler.handle(suggestion)
    }

    fun dispatchKeyAction(
        action: KeyAction
    ): KeyEventResult? {
        val inputConnection =
            currentInputConnection
                ?: return null

        val target =
            InputConnectionTarget(inputConnection)

        val executor =
            KeyActionExecutor(target)

        val dispatcher =
            KeyboardActionDispatcher(
                controller = keyboardController,
                executor = executor
            )

        return dispatcher.dispatch(action)
    }
}
