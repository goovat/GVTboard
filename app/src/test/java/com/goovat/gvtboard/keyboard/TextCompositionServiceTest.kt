package com.goovat.gvtboard.keyboard

class TextCompositionServiceTest {

    private class FakeCompositionTarget : TextCompositionTarget {

        val composingTexts = mutableListOf<String>()
        val committedTexts = mutableListOf<String>()
        var finishCount = 0

        override fun setComposingText(text: String) {
            composingTexts += text
        }

        override fun finishComposingText() {
            finishCount++
        }

        override fun commitText(text: String) {
            committedTexts += text
        }
    }

    @org.junit.Test
    fun startUpdatesStateAndCompositionTarget() {
        val controller = TextCompositionController()
        val target = FakeCompositionTarget()
        val service = TextCompositionService(controller, target)

        val state = service.start("hel")

        org.junit.Assert.assertEquals("hel", state.text)
        org.junit.Assert.assertTrue(state.isComposing)
        org.junit.Assert.assertEquals(
            listOf("hel"),
            target.composingTexts
        )
    }

    @org.junit.Test
    fun updateReplacesActiveComposition() {
        val controller = TextCompositionController()
        val target = FakeCompositionTarget()
        val service = TextCompositionService(controller, target)

        service.start("hel")
        val state = service.update("hello")

        org.junit.Assert.assertEquals("hello", state.text)
        org.junit.Assert.assertTrue(state.isComposing)
        org.junit.Assert.assertEquals(
            listOf("hel", "hello"),
            target.composingTexts
        )
    }

    @org.junit.Test
    fun commitSendsTextAndClearsState() {
        val controller = TextCompositionController()
        val target = FakeCompositionTarget()
        val service = TextCompositionService(controller, target)

        service.start("hello")
        val state = service.commit("hello")

        org.junit.Assert.assertEquals(
            listOf("hello"),
            target.committedTexts
        )

        org.junit.Assert.assertEquals(
            "",
            state.text
        )

        org.junit.Assert.assertFalse(
            state.isComposing
        )
    }

    @org.junit.Test
    fun finishEndsCompositionAndPreservesText() {
        val controller = TextCompositionController()
        val target = FakeCompositionTarget()
        val service = TextCompositionService(controller, target)

        service.start("hello")
        val state = service.finish()

        org.junit.Assert.assertEquals(
            "hello",
            state.text
        )

        org.junit.Assert.assertFalse(
            state.isComposing
        )

        org.junit.Assert.assertEquals(
            1,
            target.finishCount
        )
    }

    @org.junit.Test
    fun resetFinishesTargetAndClearsState() {
        val controller = TextCompositionController()
        val target = FakeCompositionTarget()
        val service = TextCompositionService(controller, target)

        service.start("hello")
        val state = service.reset()

        org.junit.Assert.assertEquals(
            "",
            state.text
        )

        org.junit.Assert.assertFalse(
            state.isComposing
        )

        org.junit.Assert.assertEquals(
            1,
            target.finishCount
        )
    }
}
