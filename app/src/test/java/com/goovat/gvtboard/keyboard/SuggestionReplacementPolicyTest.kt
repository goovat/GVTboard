package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class SuggestionReplacementPolicyTest {

    private val policy =
        SuggestionReplacementPolicy()

    @Test
    fun preservesLowercase() {
        assertEquals(
            "world",
            policy.replacement("wor", "world")
        )
    }

    @Test
    fun preservesInitialCapitalization() {
        assertEquals(
            "World",
            policy.replacement("Wor", "world")
        )
    }

    @Test
    fun preservesAllUppercase() {
        assertEquals(
            "WORLD",
            policy.replacement("WOR", "world")
        )
    }

    @Test
    fun handlesEmptyCurrentWord() {
        assertEquals(
            "world",
            policy.replacement("", "world")
        )
    }

    @Test
    fun handlesEmptySuggestion() {
        assertEquals(
            "",
            policy.replacement("wor", "")
        )
    }
}
