package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.goovat.gvtboard.keyboard.EditingAction
import com.goovat.gvtboard.keyboard.KeyboardMode
import com.goovat.gvtboard.keyboard.KeyboardModeController
import com.goovat.gvtboard.keyboard.SuggestionCandidate
import com.goovat.gvtboard.keyboard.SuggestionRowState

class KeyboardContainerView(
    context: Context,
    private val keyboardView: KeyboardView,
    private val modeController: KeyboardModeController,
    onSuggestionSelected: (SuggestionCandidate) -> Unit,
    onEditingAction: (EditingAction) -> Unit
) : LinearLayout(context) {

    private val suggestionRow =
        SuggestionRowView(
            context = context,
            onSuggestionSelected = onSuggestionSelected,
            onEditingRequested = {
                modeController.enterEditingMode()
                renderMode()
            }
        )

    private val editingToolbar =
        EditingToolbarView(
            context = context,
            onEditingAction = onEditingAction,
            onClose = {
                modeController.exitEditingMode()
                renderMode()
            }
        )

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        addView(
            suggestionRow,
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
        )

        addView(
            editingToolbar,
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                visibility = View.GONE
            }
        )

        addView(
            keyboardView,
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
        )

        renderMode()
    }

    fun renderSuggestions(
        state: SuggestionRowState
    ) {
        suggestionRow.render(state)
    }

    fun renderMode() {
        when (modeController.mode) {
            KeyboardMode.Keyboard -> {
                editingToolbar.visibility = View.GONE
                keyboardView.visibility = View.VISIBLE
            }

            KeyboardMode.Editing -> {
                keyboardView.visibility = View.GONE
                editingToolbar.visibility = View.VISIBLE
            }
        }
    }
}
