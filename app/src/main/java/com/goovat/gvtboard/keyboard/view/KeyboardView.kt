package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.goovat.gvtboard.keyboard.KeyDefinition
import com.goovat.gvtboard.keyboard.KeyboardController

class KeyboardView(
    context: Context,
    private val controller: KeyboardController,
    private val onKeyAction: (KeyDefinition) -> Unit,
    private val onLongPress: (KeyDefinition) -> Unit = {}
) : LinearLayout(context) {

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(
            4.dp(),
            4.dp(),
            4.dp(),
            4.dp()
        )

        render()
    }

    fun render() {
        removeAllViews()

        controller.currentLayout().forEach { row ->
            addView(
                KeyboardRowView(
                    context = context,
                    row = row,
                    onKeyAction = onKeyAction,
                    onLongPress = onLongPress,
                    keyboardState = controller.state
                ),
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            )
        }
    }

    private fun Int.dp(): Int =
        (this * resources.displayMetrics.density).toInt()
}
