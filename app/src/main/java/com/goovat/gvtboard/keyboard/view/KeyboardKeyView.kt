package com.goovat.gvtboard.keyboard.view

import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Button
import android.content.Context
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

        background = createBackground(
            appearance.normalFillColor()
        )

        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    background = createBackground(
                        appearance.pressedFillColor()
                    )
                    false
                }

                MotionEvent.ACTION_UP -> {
                    background = createBackground(
                        appearance.normalFillColor()
                    )
                    performClick()
                    true
                }

                MotionEvent.ACTION_CANCEL -> {
                    background = createBackground(
                        appearance.normalFillColor()
                    )
                    true
                }

                else -> false
            }
        }

        setOnClickListener {
            onKeyAction(keyDefinition)
        }
    }

    private fun createBackground(fillColor: Int): GradientDrawable =
        GradientDrawable().apply {
            setColor(fillColor)
            setStroke(1, appearance.strokeColor())
            cornerRadius = appearance.cornerRadiusPx()
        }
}
