package com.goovat.gvtboard.keyboard

import android.graphics.Color
import com.goovat.gvtboard.keyboard.view.KeyboardKeyAppearance
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardKeyAppearanceTest {

    private val appearance = KeyboardKeyAppearance()

    @Test
    fun normalFillColorIsWhite() {
        assertEquals(
            Color.WHITE,
            appearance.normalFillColor()
        )
    }

    @Test
    fun pressedFillColorIsLightGray() {
        assertEquals(
            Color.LTGRAY,
            appearance.pressedFillColor()
        )
    }

    @Test
    fun strokeColorIsLightGray() {
        assertEquals(
            Color.LTGRAY,
            appearance.strokeColor()
        )
    }

    @Test
    fun cornerRadiusIs12Px() {
        assertEquals(
            12f,
            appearance.cornerRadiusPx(),
            0.0f
        )
    }
}
