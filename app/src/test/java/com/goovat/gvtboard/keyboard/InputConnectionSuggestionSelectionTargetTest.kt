package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection
import java.lang.reflect.Proxy
import org.junit.Assert.assertEquals
import org.junit.Test

class InputConnectionSuggestionSelectionTargetTest {

    @Test
    fun deletesCurrentWordAndCommitsReplacement() {
        var deletedCodePoints = -1
        var committedText = ""
        var committedCursorPosition = -1

        val inputConnection = createInputConnection(
            deleteSurroundingTextInCodePoints = { before, after ->
                deletedCodePoints = before
                assertEquals(0, after)
                true
            },
            commitText = { text, cursorPosition ->
                committedText = text
                committedCursorPosition = cursorPosition
            }
        )

        val target =
            InputConnectionSuggestionSelectionTarget(
                inputConnection
            )

        target.replaceCurrentWord(
            currentWord = "wor",
            replacement = "world"
        )

        assertEquals(3, deletedCodePoints)
        assertEquals("world", committedText)
        assertEquals(1, committedCursorPosition)
    }

    @Test
    fun usesFallbackDeletionWhenCodePointDeletionFails() {
        var fallbackBeforeLength = -1
        var fallbackAfterLength = -1
        var committedText = ""

        val inputConnection = createInputConnection(
            deleteSurroundingTextInCodePoints = { _, _ ->
                false
            },
            deleteSurroundingText = { before, after ->
                fallbackBeforeLength = before
                fallbackAfterLength = after
            },
            commitText = { text, _ ->
                committedText = text
            }
        )

        val target =
            InputConnectionSuggestionSelectionTarget(
                inputConnection
            )

        target.replaceCurrentWord(
            currentWord = "wor",
            replacement = "world"
        )

        assertEquals(3, fallbackBeforeLength)
        assertEquals(0, fallbackAfterLength)
        assertEquals("world", committedText)
    }

    @Test
    fun usesCodePointCountForSupplementaryCharacters() {
        var deletedCodePoints = -1
        var committedText = ""

        val inputConnection = createInputConnection(
            deleteSurroundingTextInCodePoints = { before, after ->
                deletedCodePoints = before
                assertEquals(0, after)
                true
            },
            commitText = { text, _ ->
                committedText = text
            }
        )

        val target =
            InputConnectionSuggestionSelectionTarget(
                inputConnection
            )

        target.replaceCurrentWord(
            currentWord = "😀a",
            replacement = "hello"
        )

        assertEquals(2, deletedCodePoints)
        assertEquals("hello", committedText)
    }

    @Test
    fun doesNothingForEmptyCurrentWord() {
        var deletionCalled = false
        var commitCalled = false

        val inputConnection = createInputConnection(
            deleteSurroundingTextInCodePoints = { _, _ ->
                deletionCalled = true
                true
            },
            commitText = { _, _ ->
                commitCalled = true
            }
        )

        val target =
            InputConnectionSuggestionSelectionTarget(
                inputConnection
            )

        target.replaceCurrentWord(
            currentWord = "",
            replacement = "hello"
        )

        assertEquals(false, deletionCalled)
        assertEquals(false, commitCalled)
    }

    private fun createInputConnection(
        deleteSurroundingTextInCodePoints:
            (Int, Int) -> Boolean = { _, _ -> false },
        deleteSurroundingText:
            (Int, Int) -> Unit = { _, _ -> },
        commitText:
            (CharSequence, Int) -> Unit = { _, _ -> }
    ): InputConnection {
        return Proxy.newProxyInstance(
            InputConnection::class.java.classLoader,
            arrayOf(InputConnection::class.java)
        ) { _, method, args ->

            when (method.name) {
                "deleteSurroundingTextInCodePoints" -> {
                    deleteSurroundingTextInCodePoints(
                        args!![0] as Int,
                        args[1] as Int
                    )
                }

                "deleteSurroundingText" -> {
                    deleteSurroundingText(
                        args!![0] as Int,
                        args[1] as Int
                    )
                    true
                }

                "commitText" -> {
                    commitText(
                        args!![0] as CharSequence,
                        args[1] as Int
                    )
                    true
                }

                "toString" -> "FakeInputConnection"

                "hashCode" -> System.identityHashCode(this)

                "equals" -> this === args?.firstOrNull()

                else -> {
                    when (method.returnType) {
                        Boolean::class.javaPrimitiveType -> false
                        Int::class.javaPrimitiveType -> 0
                        Long::class.javaPrimitiveType -> 0L
                        Float::class.javaPrimitiveType -> 0f
                        Double::class.javaPrimitiveType -> 0.0
                        else -> null
                    }
                }
            }
        } as InputConnection
    }
}
