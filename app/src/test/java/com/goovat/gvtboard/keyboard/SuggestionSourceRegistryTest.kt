package com.goovat.gvtboard.keyboard

class SuggestionSourceRegistryTest {

    private class FakeSuggestionSource(
        private val candidates: List<SuggestionCandidate>
    ) : SuggestionSource {

        override fun suggest(
            currentWord: String,
            context: TextEditingContext
        ): List<SuggestionCandidate> =
            candidates
    }

    @org.junit.Test
    fun registryAggregatesSuggestionsFromAllSources() {
        val firstSource = FakeSuggestionSource(
            listOf(
                SuggestionCandidate(
                    text = "hello",
                    score = 0.9
                )
            )
        )

        val secondSource = FakeSuggestionSource(
            listOf(
                SuggestionCandidate(
                    text = "hi",
                    score = 0.8
                )
            )
        )

        val registry = SuggestionSourceRegistry(
            sources = listOf(
                firstSource,
                secondSource
            )
        )

        val suggestions = registry.suggest(
            currentWord = "h",
            context = TextEditingContext(
                textBeforeCursor = "h"
            )
        )

        org.junit.Assert.assertEquals(
            listOf(
                SuggestionCandidate(
                    text = "hello",
                    score = 0.9
                ),
                SuggestionCandidate(
                    text = "hi",
                    score = 0.8
                )
            ),
            suggestions
        )
    }

    @org.junit.Test
    fun registryPreservesSourceOrder() {
        val firstSource = FakeSuggestionSource(
            listOf(
                SuggestionCandidate("first")
            )
        )

        val secondSource = FakeSuggestionSource(
            listOf(
                SuggestionCandidate("second")
            )
        )

        val registry = SuggestionSourceRegistry(
            sources = listOf(
                firstSource,
                secondSource
            )
        )

        val suggestions = registry.suggest(
            currentWord = "test",
            context = TextEditingContext()
        )

        org.junit.Assert.assertEquals(
            listOf("first", "second"),
            suggestions.map { it.text }
        )
    }

    @org.junit.Test
    fun emptyRegistryReturnsNoSuggestions() {
        val registry = SuggestionSourceRegistry()

        val suggestions = registry.suggest(
            currentWord = "test",
            context = TextEditingContext()
        )

        org.junit.Assert.assertTrue(
            suggestions.isEmpty()
        )
    }

    @org.junit.Test
    fun registryUsesCurrentWordAndContext() {
        val source = object : SuggestionSource {

            override fun suggest(
                currentWord: String,
                context: TextEditingContext
            ): List<SuggestionCandidate> {

                org.junit.Assert.assertEquals(
                    "wor",
                    currentWord
                )

                org.junit.Assert.assertEquals(
                    "hello wor",
                    context.textBeforeCursor
                )

                return listOf(
                    SuggestionCandidate("world")
                )
            }
        }

        val registry = SuggestionSourceRegistry(
            sources = listOf(source)
        )

        registry.suggest(
            currentWord = "wor",
            context = TextEditingContext(
                textBeforeCursor = "hello wor"
            )
        )
    }
}
