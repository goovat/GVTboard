package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.view.Gravity
import android.widget.Button
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardKeyView(
    context: Context,
    private val keyDefinition: KeyDefinition,
    private val onKeyAction: (KeyDefinition) -> Unit,
    private val appearance: KeyboardKeyAppearance =
        KeyboardKeyAppearance()
) : Button(context) {

    init {
        text = keyDefinition.label
        gravity = Gravity.CENTER
        isAllCaps = false
        textSize = 16f

        background = appearance.normalBackground()

        setOnTouchListener { _, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    background = appearance.pressedBackground()
                    false
                }

                android.view.MotionEvent.ACTION_UP -> {
                    background = appearance.normalBackground()
                    performClick()
                    true
                }

                android.view.MotionEvent.ACTION_CANCEL -> {
                    background = appearance.normalBackground()
                    true
                }

                else -> false
            }
        }

        setOnClickListener {
            onKeyAction(keyDefinition)
        }
    }
}
