package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SuggestionSelectionServiceTest {

    @Test
    fun replacesCurrentWordWithSelectedSuggestion() {
        val target = FakeSuggestionSelectionTarget()

        val contextService =
            TextEditingContextService(
                FakeEditingContextTarget(
                    TextEditingContext(
                        textBeforeCursor = "I am wor",
                        selectedText = "",
                        textAfterCursor = " now"
                    )
                )
            )

        val service =
            SuggestionSelectionService(
                contextService = contextService,
                currentWordService = CurrentWordService(
                    contextService
                ),
                target = target
            )

        val selected =
            service.select(
                SuggestionCandidate("world")
            )

        assertTrue(selected)
        assertEquals(
            "wor",
            target.currentWord
        )
        assertEquals(
            "world",
            target.replacement
        )
    }

    @Test
    fun preservesInitialCapitalizationWhenSelectingSuggestion() {
        val context =
            TextEditingContext(
                textBeforeCursor = "I Wor",
                selectedText = "",
                textAfterCursor = ""
            )

        val target =
            FakeEditingContextTarget(context)

        val contextService =
            TextEditingContextService(target)

        val selectionTarget =
            FakeSuggestionSelectionTarget()

        val service =
            SuggestionSelectionService(
                contextService = contextService,
                currentWordService =
                    CurrentWordService(contextService),
                target = selectionTarget
            )

        val selected =
            service.select(
                SuggestionCandidate("world")
            )

        assertEquals(true, selected)
        assertEquals("Wor", selectionTarget.currentWord)
        assertEquals("World", selectionTarget.replacement)
    }

    @Test
    fun preservesAllUppercaseWhenSelectingSuggestion() {
        val context =
            TextEditingContext(
                textBeforeCursor = "I WOR",
                selectedText = "",
                textAfterCursor = ""
            )

        val target =
            FakeEditingContextTarget(context)

        val contextService =
            TextEditingContextService(target)

        val selectionTarget =
            FakeSuggestionSelectionTarget()

        val service =
            SuggestionSelectionService(
                contextService = contextService,
                currentWordService =
                    CurrentWordService(contextService),
                target = selectionTarget
            )

        val selected =
            service.select(
                SuggestionCandidate("world")
            )

        assertEquals(true, selected)
        assertEquals("WOR", selectionTarget.currentWord)
        assertEquals("WORLD", selectionTarget.replacement)
    }

    @Test
    fun doesNotReplaceWhenTextIsSelected() {
        val target = FakeSuggestionSelectionTarget()

        val contextService =
            TextEditingContextService(
                FakeEditingContextTarget(
                    TextEditingContext(
                        textBeforeCursor = "I am ",
                        selectedText = "wor",
                        textAfterCursor = "ld"
                    )
                )
            )

        val service =
            SuggestionSelectionService(
                contextService = contextService,
                currentWordService = CurrentWordService(
                    contextService
                ),
                target = target
            )

        val selected =
            service.select(
                SuggestionCandidate("world")
            )

        assertFalse(selected)
        assertEquals(
            "",
            target.currentWord
        )
        assertEquals(
            "",
            target.replacement
        )
    }

    @Test
    fun doesNotReplaceWhenThereIsNoCurrentWord() {
        val target = FakeSuggestionSelectionTarget()

        val contextService =
            TextEditingContextService(
                FakeEditingContextTarget(
                    TextEditingContext(
                        textBeforeCursor = "I am ",
                        selectedText = "",
                        textAfterCursor = ""
                    )
                )
            )

        val service =
            SuggestionSelectionService(
                contextService = contextService,
                currentWordService = CurrentWordService(
                    contextService
                ),
                target = target
            )

        val selected =
            service.select(
                SuggestionCandidate("hello")
            )

        assertFalse(selected)
        assertEquals(
            "",
            target.currentWord
        )
        assertEquals(
            "",
            target.replacement
        )
    }

    private class FakeSuggestionSelectionTarget :
        SuggestionSelectionTarget {

        var currentWord: String = ""
            private set

        var replacement: String = ""
            private set

        override fun replaceCurrentWord(
            currentWord: String,
            replacement: String
        ) {
            this.currentWord = currentWord
            this.replacement = replacement
        }
    }

    private class FakeEditingContextTarget(
        private val context: TextEditingContext
    ) : TextEditingContextTarget {

        override fun getTextBeforeCursor(
            maxChars: Int
        ): String =
            context.textBeforeCursor

        override fun getSelectedText(
            maxChars: Int
        ): String =
            context.selectedText

        override fun getTextAfterCursor(
            maxChars: Int
        ): String =
            context.textAfterCursor
    }
}
