package com.goovat.gvtboard.keyboard

class SuggestionSourceTest {

    private class FakeSuggestionSource : SuggestionSource {

        override fun suggest(
            currentWord: String,
            context: TextEditingContext
        ): List<SuggestionCandidate> =
            listOf(
                SuggestionCandidate(
                    text = currentWord + "ing",
                    score = 0.9
                )
            )
    }

    @org.junit.Test
    fun sourceProducesSuggestionCandidates() {
        val source = FakeSuggestionSource()

        val context = TextEditingContext(
            textBeforeCursor = "play"
        )

        val suggestions = source.suggest(
            currentWord = "play",
            context = context
        )

        org.junit.Assert.assertEquals(
            listOf(
                SuggestionCandidate(
                    text = "playing",
                    score = 0.9
                )
            ),
            suggestions
        )
    }

    @org.junit.Test
    fun sourceReceivesEditingContext() {
        val source = object : SuggestionSource {

            override fun suggest(
                currentWord: String,
                context: TextEditingContext
            ): List<SuggestionCandidate> {
                org.junit.Assert.assertEquals(
                    "hello wor",
                    context.textBeforeCursor
                )

                org.junit.Assert.assertEquals(
                    "wor",
                    currentWord
                )

                return emptyList()
            }
        }

        source.suggest(
            currentWord = "wor",
            context = TextEditingContext(
                textBeforeCursor = "hello wor"
            )
        )
    }
}
