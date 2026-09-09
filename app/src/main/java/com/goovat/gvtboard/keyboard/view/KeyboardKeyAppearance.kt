package com.goovat.gvtboard.keyboard.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

class KeyboardKeyAppearance {

    fun normalBackground(): GradientDrawable =
        background(Color.WHITE)

    fun pressedBackground(): GradientDrawable =
        background(Color.LTGRAY)

    private fun background(fillColor: Int): GradientDrawable =
        GradientDrawable().apply {
            setColor(fillColor)
            setStroke(1, Color.LTGRAY)
            cornerRadius = 12f
        }
}
