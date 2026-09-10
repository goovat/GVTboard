package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SymbolLayoutNumbersTest {

    @Test
    fun standardLayoutStartsWithNumbers() {
        val layout =
            SymbolLayout.standard()

        val labels =
            layout.rows.first().keys.map { it.label }

        assertEquals(
            listOf(
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "0"
            ),
            labels
        )
    }

    @Test
    fun standardLayoutContainsSpecialCharacters() {
        val layout =
            SymbolLayout.standard()

        val labels =
            layout.rows
                .flatMap { it.keys }
                .map { it.label }

        assertTrue(labels.contains("@"))
        assertTrue(labels.contains("#"))
        assertTrue(labels.contains("$"))
        assertTrue(labels.contains("%"))
        assertTrue(labels.contains("&"))
        assertTrue(labels.contains("?"))
    }

    @Test
    fun everyVisibleSymbolInsertsItself() {
        val layout =
            SymbolLayout.standard()

        layout.rows
            .flatMap { it.keys }
            .forEach { key ->
                val action =
                    key.action as KeyAction.InsertText

                assertEquals(
                    key.label,
                    action.text
                )
            }
    }
}
