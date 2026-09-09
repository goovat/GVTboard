package com.goovat.gvtboard.keyboard

import com.goovat.gvtboard.keyboard.view.KeyboardKeyDimensions
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardKeyDimensionsTest {

    @Test
    fun defaultHeightIs56Dp() {
        val dimensions = KeyboardKeyDimensions()

        assertEquals(56, dimensions.heightDp)
    }

    @Test
    fun defaultHorizontalMarginIs3Dp() {
        val dimensions = KeyboardKeyDimensions()

        assertEquals(3, dimensions.horizontalMarginDp)
    }

    @Test
    fun defaultVerticalMarginIs3Dp() {
        val dimensions = KeyboardKeyDimensions()

        assertEquals(3, dimensions.verticalMarginDp)
    }

    @Test
    fun dimensionsCanBeCustomized() {
        val dimensions = KeyboardKeyDimensions(
            heightDp = 60,
            horizontalMarginDp = 4,
            verticalMarginDp = 2
        )

        assertEquals(60, dimensions.heightDp)
        assertEquals(4, dimensions.horizontalMarginDp)
        assertEquals(2, dimensions.verticalMarginDp)
    }
}
