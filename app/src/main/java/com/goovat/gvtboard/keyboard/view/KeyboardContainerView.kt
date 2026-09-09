package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.goovat.gvtboard.keyboard.SuggestionCandidate
import com.goovat.gvtboard.keyboard.SuggestionRowState

class KeyboardContainerView(
    context: Context,
    keyboardView: KeyboardView,
    onSuggestionSelected: (SuggestionCandidate) -> Unit
) : LinearLayout(context) {

    private val suggestionRow =
        SuggestionRowView(
            context = context,
            onSuggestionSelected = onSuggestionSelected
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
}
