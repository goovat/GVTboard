package com.goovat.gvtboard.keyboard

class SymbolLayoutTest {

    @org.junit.Test
    fun standardLayoutHasThreeRows() {
        val layout = SymbolLayout.standard()

        org.junit.Assert.assertEquals(3, layout.rows.size)
    }

    @org.junit.Test
    fun firstRowContainsCommonSymbols() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows[0].keys.map { it.label }

        org.junit.Assert.assertEquals(
            listOf("!", "@", "#", "$", "%", "^", "&", "*", "(", ")"),
            labels
        )
    }

    @org.junit.Test
    fun secondRowContainsOperatorSymbols() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows[1].keys.map { it.label }

        org.junit.Assert.assertEquals(
            listOf("-", "_", "+", "=", "/", "\\", "|", "~"),
            labels
        )
    }

    @org.junit.Test
    fun thirdRowContainsPunctuationSymbols() {
        val layout = SymbolLayout.standard()

        val labels = layout.rows[2].keys.map { it.label }

        org.junit.Assert.assertEquals(
            listOf(
                "[", "]", "{", "}", "<", ">", ":",
                ";", "'", "\"", ",", ".", "?"
            ),
            labels
        )
    }

    @org.junit.Test
    fun everySymbolKeyInsertsItsOwnSymbol() {
        val layout = SymbolLayout.standard()

        val symbolKeys = layout.rows
            .flatMap { it.keys }

        symbolKeys.forEach { key ->
            val action = key.action as KeyAction.InsertText

            org.junit.Assert.assertEquals(
                key.label,
                action.text
            )
        }
    }

    @org.junit.Test
    fun standardLayoutContainsExactly31Symbols() {
        val layout = SymbolLayout.standard()

        val symbols = layout.rows
            .flatMap { it.keys }
            .map { it.label }

        org.junit.Assert.assertEquals(31, symbols.size)
        org.junit.Assert.assertEquals(31, symbols.toSet().size)
    }
}
