package com.goovat.gvtboard.keyboard

class TextCompositionControllerTest {

    @org.junit.Test
    fun initialStateIsNotComposing() {
        val controller = TextCompositionController()

        org.junit.Assert.assertEquals(
            "",
            controller.state.text
        )

        org.junit.Assert.assertFalse(
            controller.state.isComposing
        )
    }

    @org.junit.Test
    fun startCreatesActiveComposition() {
        val controller = TextCompositionController()

        val state = controller.start("hel")

        org.junit.Assert.assertEquals(
            "hel",
            state.text
        )

        org.junit.Assert.assertTrue(
            state.isComposing
        )
    }

    @org.junit.Test
    fun updateReplacesCompositionText() {
        val controller = TextCompositionController()

        controller.start("hel")

        val state = controller.update("hello")

        org.junit.Assert.assertEquals(
            "hello",
            state.text
        )

        org.junit.Assert.assertTrue(
            state.isComposing
        )
    }

    @org.junit.Test
    fun finishEndsCompositionWithoutChangingText() {
        val controller = TextCompositionController()

        controller.start("hello")

        val state = controller.finish()

        org.junit.Assert.assertEquals(
            "hello",
            state.text
        )

        org.junit.Assert.assertFalse(
            state.isComposing
        )
    }

    @org.junit.Test
    fun clearResetsCompositionState() {
        val controller = TextCompositionController()

        controller.start("hello")
        controller.clear()

        org.junit.Assert.assertEquals(
            "",
            controller.state.text
        )

        org.junit.Assert.assertFalse(
            controller.state.isComposing
        )
    }
}
