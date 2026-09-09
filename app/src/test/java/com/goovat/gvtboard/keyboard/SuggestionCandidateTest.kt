package com.goovat.gvtboard.keyboard

class SuggestionCandidateTest {

    @org.junit.Test
    fun candidateStoresTextAndScore() {
        val candidate = SuggestionCandidate(
            text = "hello",
            score = 0.95
        )

        org.junit.Assert.assertEquals(
            "hello",
            candidate.text
        )

        org.junit.Assert.assertEquals(
            0.95,
            candidate.score,
            0.0
        )
    }

    @org.junit.Test
    fun candidateDefaultsScoreToZero() {
        val candidate = SuggestionCandidate(
            text = "hello"
        )

        org.junit.Assert.assertEquals(
            0.0,
            candidate.score,
            0.0
        )
    }

    @org.junit.Test
    fun candidateRejectsEmptyText() {
        try {
            SuggestionCandidate("")
            org.junit.Assert.fail(
                "Expected IllegalArgumentException"
            )
        } catch (_: IllegalArgumentException) {
        }
    }

    @org.junit.Test
    fun candidateRejectsNonFiniteScore() {
        try {
            SuggestionCandidate(
                text = "hello",
                score = Double.NaN
            )
            org.junit.Assert.fail(
                "Expected IllegalArgumentException"
            )
        } catch (_: IllegalArgumentException) {
        }

        try {
            SuggestionCandidate(
                text = "hello",
                score = Double.POSITIVE_INFINITY
            )
            org.junit.Assert.fail(
                "Expected IllegalArgumentException"
            )
        } catch (_: IllegalArgumentException) {
        }
    }
}
