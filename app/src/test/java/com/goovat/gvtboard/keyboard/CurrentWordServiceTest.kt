package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrentWordServiceTest {

    @Test
    fun readsCurrentWordFromProvidedContext() {
        val target = FakeEditingContextTarget(
            context = TextEditingContext(
                textBeforeCursor = "hello wor",
                selectedText = "",
                textAfterCursor = "ld"
            )
        )

        val contextService = TextEditingContextService(target)
        val service = CurrentWordService(contextService)

        val result = service.readCurrentWord(
            TextEditingContext(
                textBeforeCursor = "testing wor",
                selectedText = "",
                textAfterCursor = "d"
            )
        )

        assertEquals("wor", result)
        assertEquals(0, target.readCount)
    }

    @Test
    fun readsCurrentWordFromContextServiceWhenNoContextProvided() {
        val target = FakeEditingContextTarget(
            context = TextEditingContext(
                textBeforeCursor = "hello wor",
                selectedText = "",
                textAfterCursor = "ld"
            )
        )

        val contextService = TextEditingContextService(target)
        val service = CurrentWordService(contextService)

        val result = service.readCurrentWord()

        assertEquals("wor", result)
        assertEquals(1, target.readCount)
    }

    private class FakeEditingContextTarget(
        private val context: TextEditingContext
    ) : TextEditingContextTarget {

        var readCount: Int = 0
            private set

        override fun getTextBeforeCursor(maxChars: Int): String {
            readCount++
            return context.textBeforeCursor
        }

        override fun getSelectedText(maxChars: Int): String =
            context.selectedText

        override fun getTextAfterCursor(maxChars: Int): String =
            context.textAfterCursor
    }
}
