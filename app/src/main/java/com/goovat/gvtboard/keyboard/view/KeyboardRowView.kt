package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.goovat.gvtboard.keyboard.KeyAction
import com.goovat.gvtboard.keyboard.KeyDefinition
import com.goovat.gvtboard.keyboard.KeyboardState
import com.goovat.gvtboard.keyboard.KeyboardRow

class KeyboardRowView(
    context: Context,
    row: KeyboardRow,
    onKeyAction: (KeyDefinition) -> Unit,
    private val keyboardState: KeyboardState = KeyboardState(),
    onLongPress: (KeyDefinition) -> Unit = {},
    private val sizingPolicy: KeyboardKeySizingPolicy =
        KeyboardKeySizingPolicy(),
    private val dimensions: KeyboardKeyDimensions =
        KeyboardKeyDimensions()
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
                    onKeyAction = onKeyAction,
                    onLongPress = onLongPress,
                    isActive =
                        key.action == KeyAction.Shift &&
                            keyboardState.isShifted
                ),
                LinearLayout.LayoutParams(
                    0,
                    dimensions.heightDp.dp(context),
                    sizingPolicy.weight(key)
                ).apply {
                    setMargins(
                        dimensions.horizontalMarginDp.dp(context),
                        dimensions.verticalMarginDp.dp(context),
                        dimensions.horizontalMarginDp.dp(context),
                        dimensions.verticalMarginDp.dp(context)
                    )
                }
            )
        }
    }

    private fun Int.dp(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()
}
