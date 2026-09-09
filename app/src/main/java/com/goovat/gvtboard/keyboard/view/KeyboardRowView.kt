package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.goovat.gvtboard.keyboard.KeyDefinition
import com.goovat.gvtboard.keyboard.KeyboardRow

class KeyboardRowView(
    context: Context,
    row: KeyboardRow,
    onKeyAction: (KeyDefinition) -> Unit,
    private val sizingPolicy: KeyboardKeySizingPolicy =
        KeyboardKeySizingPolicy()
) : LinearLayout(context) {

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER

        val totalWeight = row.keys.sumOf {
            sizingPolicy.weight(it).toDouble()
        }.toFloat()

        weightSum = totalWeight

        row.keys.forEach { key ->
            addView(
                KeyboardKeyView(
                    context = context,
                    keyDefinition = key,
                    onKeyAction = onKeyAction
                ),
                LinearLayout.LayoutParams(
                    0,
                    56.dp(context),
                    sizingPolicy.weight(key)
                ).apply {
                    setMargins(
                        3.dp(context),
                        3.dp(context),
                        3.dp(context),
                        3.dp(context)
                    )
                }
            )
        }
    }

    private fun Int.dp(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()
}
