package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import com.goovat.gvtboard.keyboard.EditingAction

class EditingToolbarView(
    context: Context,
    private val onEditingAction: (EditingAction) -> Unit,
    private val onClose: () -> Unit
) : LinearLayout(context) {

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(
            8.dp(),
            8.dp(),
            8.dp(),
            8.dp()
        )

        addView(
            createHeader(),
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                48.dp()
            )
        )

        addView(
            createEditingGrid(),
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
        )
    }

    private fun createHeader(): LinearLayout =
        LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL

            addView(
                Button(context).apply {
                    text = "←"
                    isAllCaps = false
                    textSize = 18f
                    setTextColor(Color.BLACK)
                    setOnClickListener {
                        onClose()
                    }
                },
                LinearLayout.LayoutParams(
                    56.dp(),
                    LayoutParams.MATCH_PARENT
                )
            )

            addView(
                Button(context).apply {
                    text = "Text editing"
                    isAllCaps = false
                    textSize = 16f
                    setTextColor(Color.BLACK)
                    isClickable = false
                    isFocusable = false
                },
                LinearLayout.LayoutParams(
                    0,
                    LayoutParams.MATCH_PARENT,
                    1f
                )
            )
        }

    private fun createEditingGrid(): LinearLayout =
        LinearLayout(context).apply {
            orientation = VERTICAL
            gravity = Gravity.CENTER

            addRow(
                listOf(
                    "←" to EditingAction.CursorLeft,
                    "→" to EditingAction.CursorRight,
                    "↑" to EditingAction.CursorUp,
                    "↓" to EditingAction.CursorDown
                )
            )

            addRow(
                listOf(
                    "Select" to EditingAction.Select,
                    "Select all" to EditingAction.SelectAll,
                    "Copy" to EditingAction.Copy,
                    "Cut" to EditingAction.Cut
                )
            )

            addRow(
                listOf(
                    "Paste" to EditingAction.Paste,
                    "Undo" to EditingAction.Undo,
                    "Redo" to EditingAction.Redo,
                    "←|" to EditingAction.MoveToBeginning
                )
            )

            addRow(
                listOf(
                    "|→" to EditingAction.MoveToEnd
                )
            )
        }

    private fun LinearLayout.addRow(
        actions: List<Pair<String, EditingAction>>
    ) {
        val row = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER
        }

        actions.forEach { (label, action) ->
            row.addView(
                Button(context).apply {
                    text = label
                    isAllCaps = false
                    textSize = 14f
                    setTextColor(Color.BLACK)
                    setOnClickListener {
                        onEditingAction(action)
                    }
                },
                LinearLayout.LayoutParams(
                    0,
                    52.dp(),
                    1f
                ).apply {
                    setMargins(
                        2.dp(),
                        2.dp(),
                        2.dp(),
                        2.dp()
                    )
                }
            )
        }

        addView(
            row,
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                56.dp()
            )
        )
    }

    private fun Int.dp(): Int =
        (this * resources.displayMetrics.density).toInt()
}
