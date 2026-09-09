package com.goovat.gvtboard.keyboard

class TextEditingContextServiceTest {

    private class FakeEditingContextTarget(
        private val context: TextEditingContext
    ) : TextEditingContextTarget {

        var beforeCursorLimit: Int? = null
        var selectedLimit: Int? = null
        var afterCursorLimit: Int? = null

        override fun getTextBeforeCursor(maxChars: Int): String {
            beforeCursorLimit = maxChars
            return context.textBeforeCursor
        }

        override fun getSelectedText(maxChars: Int): String {
            selectedLimit = maxChars
            return context.selectedText
        }

        override fun getTextAfterCursor(maxChars: Int): String {
            afterCursorLimit = maxChars
            return context.textAfterCursor
        }
    }

    @org.junit.Test
    fun readReturnsCurrentEditingContext() {
        val expected = TextEditingContext(
            textBeforeCursor = "hello ",
            selectedText = "world",
            textAfterCursor = "!"
        )

        val target = FakeEditingContextTarget(expected)
        val service = TextEditingContextService(target)

        org.junit.Assert.assertEquals(
            expected,
            service.read()
        )
    }

    @org.junit.Test
    fun readUsesConfiguredPolicyLimits() {
        val target = FakeEditingContextTarget(
            TextEditingContext()
        )

        val policy = TextEditingContextPolicy(
            maxBeforeCursorChars = 80,
            maxSelectedChars = 40,
            maxAfterCursorChars = 96
        )

        val service = TextEditingContextService(
            target = target,
            policy = policy
        )

        service.read()

        org.junit.Assert.assertEquals(
            80,
            target.beforeCursorLimit
        )

        org.junit.Assert.assertEquals(
            40,
            target.selectedLimit
        )

        org.junit.Assert.assertEquals(
            96,
            target.afterCursorLimit
        )
    }

    @org.junit.Test
    fun policyRejectsNonPositiveLimits() {
        try {
            TextEditingContextPolicy(
                maxBeforeCursorChars = 0
            )
            org.junit.Assert.fail(
                "Expected IllegalArgumentException"
            )
        } catch (_: IllegalArgumentException) {
        }

        try {
            TextEditingContextPolicy(
                maxSelectedChars = 0
            )
            org.junit.Assert.fail(
                "Expected IllegalArgumentException"
            )
        } catch (_: IllegalArgumentException) {
        }

        try {
            TextEditingContextPolicy(
                maxAfterCursorChars = 0
            )
            org.junit.Assert.fail(
                "Expected IllegalArgumentException"
            )
        } catch (_: IllegalArgumentException) {
        }
    }
}
