package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.goovat.gvtboard.keyboard.EditingAction
import com.goovat.gvtboard.keyboard.SuggestionCandidate
import com.goovat.gvtboard.keyboard.SuggestionRowState

class KeyboardContainerView(
    context: Context,
    private val keyboardView: KeyboardView,
    onSuggestionSelected: (SuggestionCandidate) -> Unit,
    onEditingAction: (EditingAction) -> Unit
) : LinearLayout(context) {

    private val suggestionRow =
        SuggestionRowView(
            context = context,
            onSuggestionSelected = onSuggestionSelected,
            onEditingRequested = {
                showEditingPanel()
            }
        )

    private val editingToolbar =
        EditingToolbarView(
            context = context,
            onEditingAction = onEditingAction,
            onClose = {
                showKeyboard()
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
    }

    fun renderSuggestions(
        state: SuggestionRowState
    ) {
        suggestionRow.render(state)
    }

    private fun showEditingPanel() {
        keyboardView.visibility = View.GONE
        editingToolbar.visibility = View.VISIBLE
    }

    private fun showKeyboard() {
        editingToolbar.visibility = View.GONE
        keyboardView.visibility = View.VISIBLE
    }
}
