package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.Button
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardKeyView(
    context: Context,
    private val keyDefinition: KeyDefinition,
    private val onKeyAction: (KeyDefinition) -> Unit
) : Button(context) {

    init {
        text = keyDefinition.label
        gravity = Gravity.CENTER
        isAllCaps = false
        textSize = 16f

        background = GradientDrawable().apply {
            setColor(Color.WHITE)
            setStroke(1, Color.LTGRAY)
            cornerRadius = 12f
        }

        setOnClickListener {
            onKeyAction(keyDefinition)
        }
    }
}
