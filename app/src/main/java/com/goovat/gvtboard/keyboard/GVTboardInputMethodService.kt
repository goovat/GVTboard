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

    private val suggestionRefresher =
        KeyboardSuggestionRefresher()

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
        refreshSuggestions()
    }

    override fun onFinishInput() {
        inputLifecycle.finishInput()

        keyboardView?.render()

        keyboardContainerView?.renderSuggestions(
            SuggestionRowState()
        )

        super.onFinishInput()
    }

    override fun onCreateInputView(): View {
        val keyboard =
            KeyboardView(
                context = this,
                controller = keyboardController,
                onKeyAction = { keyDefinition ->
                    dispatchKeyAction(
                        keyDefinition.action
                    )
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
            },
            onEditingAction = { action ->
                dispatchEditingAction(action)
            }
        ).also {
            keyboardContainerView = it
        }
    }

    private fun refreshSuggestions() {
        val inputConnection =
            currentInputConnection
                ?: run {
                    keyboardContainerView?.renderSuggestions(
                        SuggestionRowState()
                    )
                    return
                }

        val state =
            suggestionRefresher.refresh(
                inputConnection
            )

        keyboardContainerView?.renderSuggestions(state)
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

        val contextService =
            TextEditingContextService(
                InputConnectionEditingContextTarget(
                    inputConnection
                )
            )

        val coordinator =
            SuggestionSelectionCoordinator(
                SuggestionSelectionService(
                    contextService = contextService,
                    currentWordService =
                        CurrentWordService(
                            contextService
                        ),
                    target = selectionTarget
                )
            )

        val handler =
            SuggestionRowSelectionHandler(
                coordinator
            )

        val selected =
            handler.handle(suggestion)

        if (selected) {
            refreshSuggestions()
        }

        return selected
    }

    fun dispatchKeyAction(
        action: KeyAction
    ): KeyEventResult? {

        val inputConnection =
            currentInputConnection

        if (inputConnection != null) {
            val target =
                InputConnectionTarget(inputConnection)

            val executor =
                KeyActionExecutor(target)

            val dispatcher =
                KeyboardActionDispatcher(
                    controller = keyboardController,
                    executor = executor
                )

            dispatcher.dispatch(action)
        } else {
            keyboardController.handle(action)
        }

        keyboardView?.render()
        refreshSuggestions()

        return KeyEventResult(
            state = keyboardController.state,
            action = action
        )
    }

    private fun dispatchEditingAction(
        action: EditingAction
    ) {
        val inputConnection =
            currentInputConnection
                ?: return

        val target =
            InputConnectionTarget(inputConnection)

        EditingActionExecutor(target)
            .execute(action)

        refreshSuggestions()
    }
}
