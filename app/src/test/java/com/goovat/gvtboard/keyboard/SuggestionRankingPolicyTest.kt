package com.goovat.gvtboard.keyboard

class SuggestionRankingPolicyTest {

    @org.junit.Test
    fun ranksCandidatesByDescendingScore() {
        val policy = SuggestionRankingPolicy()

        val result = policy.rank(
            listOf(
                SuggestionCandidate("low", 0.2),
                SuggestionCandidate("high", 0.9),
                SuggestionCandidate("medium", 0.5)
            )
        )

        org.junit.Assert.assertEquals(
            listOf("high", "medium", "low"),
            result.map { it.text }
        )
    }

    @org.junit.Test
    fun removesDuplicateSuggestionText() {
        val policy = SuggestionRankingPolicy()

        val result = policy.rank(
            listOf(
                SuggestionCandidate("hello", 0.4),
                SuggestionCandidate("hello", 0.9),
                SuggestionCandidate("hi", 0.8)
            )
        )

        org.junit.Assert.assertEquals(
            listOf("hello", "hi"),
            result.map { it.text }
        )
    }

    @org.junit.Test
    fun keepsHighestScoringDuplicate() {
        val policy = SuggestionRankingPolicy()

        val result = policy.rank(
            listOf(
                SuggestionCandidate("hello", 0.4),
                SuggestionCandidate("hello", 0.9)
            )
        )

        org.junit.Assert.assertEquals(
            "hello",
            result.single().text
        )

        org.junit.Assert.assertEquals(
            0.9,
            result.single().score,
            0.0
        )
    }

    @org.junit.Test
    fun limitsSuggestionsToTwentyByDefault() {
        val policy = SuggestionRankingPolicy()

        val candidates = (1..30).map {
            SuggestionCandidate(
                text = "word$it",
                score = it.toDouble()
            )
        }

        val result = policy.rank(candidates)

        org.junit.Assert.assertEquals(
            20,
            result.size
        )

        org.junit.Assert.assertEquals(
            "word30",
            result.first().text
        )

        org.junit.Assert.assertEquals(
            "word11",
            result.last().text
        )
    }

    @org.junit.Test
    fun supportsConfiguredMaximum() {
        val policy = SuggestionRankingPolicy(
            maxSuggestions = 3
        )

        val result = policy.rank(
            listOf(
                SuggestionCandidate("one", 0.9),
                SuggestionCandidate("two", 0.8),
                SuggestionCandidate("three", 0.7),
                SuggestionCandidate("four", 0.6)
            )
        )

        org.junit.Assert.assertEquals(
            listOf("one", "two", "three"),
            result.map { it.text }
        )
    }

    @org.junit.Test
    fun rejectsNonPositiveMaximum() {
        try {
            SuggestionRankingPolicy(maxSuggestions = 0)
            org.junit.Assert.fail(
                "Expected IllegalArgumentException"
            )
        } catch (_: IllegalArgumentException) {
        }
    }

    @org.junit.Test
    fun emptyCandidatesProduceEmptyResult() {
        val policy = SuggestionRankingPolicy()

        org.junit.Assert.assertTrue(
            policy.rank(emptyList()).isEmpty()
        )
    }
}
