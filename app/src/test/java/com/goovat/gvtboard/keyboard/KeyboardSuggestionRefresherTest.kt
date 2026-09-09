package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardSuggestionRefresherTest {

    @Test
    fun buildsSuggestionsForCurrentWord() {
        val context =
            TextEditingContext(
                textBeforeCursor = "I wor",
                selectedText = "",
                textAfterCursor = ""
            )

        val contextService =
            TextEditingContextService(
                FakeEditingContextTarget(context)
            )

        val currentWordService =
            CurrentWordService(contextService)

        val registry =
            SuggestionSourceRegistry(
                listOf(
                    PrefixSuggestionSource(
                        listOf(
                            "world",
                            "word",
                            "work"
                        )
                    )
                )
            )

        val service =
            SuggestionService(
                contextService = contextService,
                currentWordService = currentWordService,
                sourceRegistry = registry
            )

        val result =
            SuggestionRowPolicy()
                .createState(service.suggest())

        assertEquals(
            listOf("word", "world", "work"),
            result.suggestions.map { it.text }
        )
    }

    @Test
    fun emptyCurrentWordProducesEmptySuggestionRow() {
        val context =
            TextEditingContext(
                textBeforeCursor = "I ",
                selectedText = "",
                textAfterCursor = ""
            )

        val contextService =
            TextEditingContextService(
                FakeEditingContextTarget(context)
            )

        val service =
            SuggestionService(
                contextService = contextService,
                currentWordService =
                    CurrentWordService(contextService),
                sourceRegistry =
                    SuggestionSourceRegistry(
                        listOf(
                            PrefixSuggestionSource(
                                listOf("world", "work")
                            )
                        )
                    )
            )

        val result =
            SuggestionRowPolicy()
                .createState(service.suggest())

        assertEquals(
            emptyList<SuggestionCandidate>(),
            result.suggestions
        )
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
