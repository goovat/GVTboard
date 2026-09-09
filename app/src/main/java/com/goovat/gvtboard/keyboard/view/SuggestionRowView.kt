package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import com.goovat.gvtboard.keyboard.SuggestionCandidate
import com.goovat.gvtboard.keyboard.SuggestionRowState

class SuggestionRowView(
    context: Context,
    private val onSuggestionSelected:
        (SuggestionCandidate) -> Unit
) : HorizontalScrollView(context) {

    private val container =
        LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

    init {
        isHorizontalScrollBarEnabled = false
        addView(container)
    }

    fun render(state: SuggestionRowState) {
        container.removeAllViews()

        state.suggestions.forEach { suggestion ->
            container.addView(
                createSuggestionView(suggestion)
            )
        }
    }

    private fun createSuggestionView(
        suggestion: SuggestionCandidate
    ): TextView =
        TextView(context).apply {
            text = suggestion.text
            gravity = Gravity.CENTER
            textSize = 16f
            setTextColor(Color.BLACK)
            setPadding(
                24.dp(),
                12.dp(),
                24.dp(),
                12.dp()
            )

            setOnClickListener {
                onSuggestionSelected(suggestion)
            }
        }

    private fun Int.dp(): Int =
        (this * resources.displayMetrics.density)
            .toInt()
}
