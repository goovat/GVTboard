package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class SymbolLayoutTest {

    @Test
    fun standardLayoutHasFourRows() {
        val layout = SymbolLayout.standard()

        assertEquals(4, layout.rows.size)
    }

    @Test
    fun firstRowContainsNumbers() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows[0].keys.map { it.label }

        assertEquals(
            listOf(
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "0"
            ),
            labels
        )
    }

    @Test
    fun secondRowContainsCommonSymbols() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows[1].keys.map { it.label }

        assertEquals(
            listOf("!", "@", "#", "$", "%", "^", "&", "*", "(", ")"),
            labels
        )
    }

    @Test
    fun thirdRowContainsOperatorSymbols() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows[2].keys.map { it.label }

        assertEquals(
            listOf("-", "_", "+", "=", "/", "\\", "|", "~"),
            labels
        )
    }

    @Test
    fun fourthRowContainsPunctuationSymbols() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows[3].keys.map { it.label }

        assertEquals(
            listOf(
                "[", "]", "{", "}", "<", ">", ":",
                ";", "'", "\"", ",", ".", "?"
            ),
            labels
        )
    }

    @Test
    fun everySymbolKeyInsertsItsOwnSymbol() {
        val layout = SymbolLayout.standard()

        layout.rows
            .flatMap { it.keys }
            .forEach { key ->
                val action = key.action as KeyAction.InsertText

                assertEquals(
                    key.label,
                    action.text
                )
            }
    }

    @Test
    fun standardLayoutContainsExactly41Keys() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows
            .flatMap { it.keys }
            .map { it.label }

        assertEquals(41, labels.size)
        assertEquals(41, labels.toSet().size)
    }
}
