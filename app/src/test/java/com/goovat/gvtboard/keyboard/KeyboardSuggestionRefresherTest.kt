package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection
import java.lang.reflect.Proxy
import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardSuggestionRefresherTest {

    @Test
    fun refreshBuildsSuggestionsForCurrentWord() {
        val inputConnection =
            fakeInputConnection(
                textBeforeCursor = "I wor",
                selectedText = "",
                textAfterCursor = ""
            )

        val refresher =
            KeyboardSuggestionRefresher(
                sourceRegistry =
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
            )

        val result =
            refresher.refresh(inputConnection)

        assertEquals(
            listOf("word", "work", "world"),
            result.suggestions.map { it.text }
        )
    }

    @Test
    fun refreshReturnsEmptyRowForEmptyCurrentWord() {
        val inputConnection =
            fakeInputConnection(
                textBeforeCursor = "I ",
                selectedText = "",
                textAfterCursor = ""
            )

        val refresher =
            KeyboardSuggestionRefresher(
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
            refresher.refresh(inputConnection)

        assertEquals(
            emptyList<SuggestionCandidate>(),
            result.suggestions
        )
    }

    private fun fakeInputConnection(
        textBeforeCursor: String,
        selectedText: String,
        textAfterCursor: String
    ): InputConnection =
        Proxy.newProxyInstance(
            InputConnection::class.java.classLoader,
            arrayOf(InputConnection::class.java)
        ) { _, method, _ ->
            when (method.name) {
                "getTextBeforeCursor" ->
                    textBeforeCursor

                "getSelectedText" ->
                    selectedText

                "getTextAfterCursor" ->
                    textAfterCursor

                else ->
                    defaultValue(method.returnType)
            }
        } as InputConnection

    private fun defaultValue(
        returnType: Class<*>
    ): Any? =
        when (returnType) {
            Boolean::class.javaPrimitiveType ->
                false

            Int::class.javaPrimitiveType ->
                0

            Long::class.javaPrimitiveType ->
                0L

            Float::class.javaPrimitiveType ->
                0f

            Double::class.javaPrimitiveType ->
                0.0

            else ->
                null
        }
}
