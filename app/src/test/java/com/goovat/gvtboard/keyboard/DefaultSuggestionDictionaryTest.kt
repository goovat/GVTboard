package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultSuggestionDictionaryTest {

    @Test
    fun containsCommonSuggestionWords() {
        assertTrue(
            DefaultSuggestionDictionary.words.contains("world")
        )

        assertTrue(
            DefaultSuggestionDictionary.words.contains("work")
        )

        assertTrue(
            DefaultSuggestionDictionary.words.contains("hello")
        )
    }

    @Test
    fun doesNotContainBlankWords() {
        assertFalse(
            DefaultSuggestionDictionary.words.any {
                it.isBlank()
            }
        )
    }
}
