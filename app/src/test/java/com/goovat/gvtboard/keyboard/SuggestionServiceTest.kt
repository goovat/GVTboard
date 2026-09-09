package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class SuggestionServiceTest {

    @Test
    fun readsContextOnceAndReturnsRankedSuggestions() {
        val context = TextEditingContext(
            textBeforeCursor = "hello wor",
            selectedText = "",
            textAfterCursor = "ld"
        )

        val target = FakeEditingContextTarget(context)
        val contextService = TextEditingContextService(target)
        val currentWordService = CurrentWordService(contextService)

        val source = RecordingSuggestionSource(
            candidates = listOf(
                SuggestionCandidate("world", 0.7),
                SuggestionCandidate("work", 0.9),
                SuggestionCandidate("word", 0.8)
            )
        )

        val registry = SuggestionSourceRegistry(
            listOf(source)
        )

        val service = SuggestionService(
            contextService = contextService,
            currentWordService = currentWordService,
            sourceRegistry = registry
        )

        val result = service.suggest()

        assertEquals(
            listOf("work", "word", "world"),
            result.map { it.text }
        )

        assertEquals(1, target.readCount)
        assertEquals("wor", source.currentWord)
        assertEquals(context, source.context)
    }

    @Test
    fun returnsEmptyWhenNoSourcesProduceSuggestions() {
        val context = TextEditingContext(
            textBeforeCursor = "hello wor"
        )

        val target = FakeEditingContextTarget(context)
        val contextService = TextEditingContextService(target)
        val currentWordService = CurrentWordService(contextService)

        val service = SuggestionService(
            contextService = contextService,
            currentWordService = currentWordService,
            sourceRegistry = SuggestionSourceRegistry()
        )

        assertEquals(emptyList<SuggestionCandidate>(), service.suggest())
        assertEquals(1, target.readCount)
    }

    @Test
    fun duplicateSuggestionsAreRankedUsingHighestScore() {
        val context = TextEditingContext(
            textBeforeCursor = "hello wor"
        )

        val target = FakeEditingContextTarget(context)
        val contextService = TextEditingContextService(target)
        val currentWordService = CurrentWordService(contextService)

        val source = RecordingSuggestionSource(
            candidates = listOf(
                SuggestionCandidate("world", 0.4),
                SuggestionCandidate("world", 0.95),
                SuggestionCandidate("work", 0.8)
            )
        )

        val service = SuggestionService(
            contextService = contextService,
            currentWordService = currentWordService,
            sourceRegistry = SuggestionSourceRegistry(
                listOf(source)
            )
        )

        val result = service.suggest()

        assertEquals(
            listOf("world", "work"),
            result.map { it.text }
        )

        assertEquals(
            listOf(0.95, 0.8),
            result.map { it.score }
        )
    }

    private class RecordingSuggestionSource(
        private val candidates: List<SuggestionCandidate>
    ) : SuggestionSource {

        var currentWord: String? = null
            private set

        var context: TextEditingContext? = null
            private set

        override fun suggest(
            currentWord: String,
            context: TextEditingContext
        ): List<SuggestionCandidate> {
            this.currentWord = currentWord
            this.context = context
            return candidates
        }
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
