package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardKeyAppearance
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardKeyAppearanceTest {

    private val appearance = KeyboardKeyAppearance()

    @Test
    fun normalBackgroundUsesWhiteFill() {
        val background = appearance.normalBackground()

        assertEquals(
            0xFFFFFFFF.toInt(),
            background.color?.defaultColor
        )
    }

    @Test
    fun pressedBackgroundUsesLightGrayFill() {
        val background = appearance.pressedBackground()

        assertEquals(
            0xFFCCCCCC.toInt(),
            background.color?.defaultColor
        )
    }
}
