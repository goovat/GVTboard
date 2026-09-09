package com.goovat.gvtboard.keyboard

class CurrentWordDetectorTest {

    @org.junit.Test
    fun detectsWordImmediatelyBeforeCursor() {
        val detector = CurrentWordDetector()

        val context = TextEditingContext(
            textBeforeCursor = "hello wor"
        )

        org.junit.Assert.assertEquals(
            "wor",
            detector.detect(context)
        )
    }

    @org.junit.Test
    fun detectsCompleteWordAtCursor() {
        val detector = CurrentWordDetector()

        val context = TextEditingContext(
            textBeforeCursor = "hello world"
        )

        org.junit.Assert.assertEquals(
            "world",
            detector.detect(context)
        )
    }

    @org.junit.Test
    fun returnsEmptyWhenCursorFollowsWhitespace() {
        val detector = CurrentWordDetector()

        val context = TextEditingContext(
            textBeforeCursor = "hello "
        )

        org.junit.Assert.assertEquals(
            "",
            detector.detect(context)
        )
    }

    @org.junit.Test
    fun returnsEmptyForEmptyContext() {
        val detector = CurrentWordDetector()

        org.junit.Assert.assertEquals(
            "",
            detector.detect(TextEditingContext())
        )
    }

    @org.junit.Test
    fun supportsWordsContainingApostrophes() {
        val detector = CurrentWordDetector()

        val context = TextEditingContext(
            textBeforeCursor = "I can't"
        )

        org.junit.Assert.assertEquals(
            "can't",
            detector.detect(context)
        )
    }

    @org.junit.Test
    fun supportsNumbersInsideWords() {
        val detector = CurrentWordDetector()

        val context = TextEditingContext(
            textBeforeCursor = "version2"
        )

        org.junit.Assert.assertEquals(
            "version2",
            detector.detect(context)
        )
    }

    @org.junit.Test
    fun selectedTextPreventsCurrentWordDetection() {
        val detector = CurrentWordDetector()

        val context = TextEditingContext(
            textBeforeCursor = "hello wor",
            selectedText = "wor"
        )

        org.junit.Assert.assertEquals(
            "",
            detector.detect(context)
        )
    }
}
