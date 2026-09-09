package com.goovat.gvtboard.keyboard

class KeyboardLayoutTest {

    @org.junit.Test
    fun alphabeticLayoutHasFourRows() {
        val layout = KeyboardLayout.alphabetic()

        org.junit.Assert.assertEquals(4, layout.rows.size)
    }

    @org.junit.Test
    fun firstRowContainsQwertyLetters() {
        val layout = KeyboardLayout.alphabetic()

        val labels = layout.rows[0].keys.map { it.label }

        org.junit.Assert.assertEquals(
            listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
            labels
        )
    }

    @org.junit.Test
    fun secondRowContainsHomeRowLetters() {
        val layout = KeyboardLayout.alphabetic()

        val labels = layout.rows[1].keys.map { it.label }

        org.junit.Assert.assertEquals(
            listOf("a", "s", "d", "f", "g", "h", "j", "k", "l"),
            labels
        )
    }

    @org.junit.Test
    fun thirdRowContainsShiftLettersAndBackspace() {
        val layout = KeyboardLayout.alphabetic()

        val row = layout.rows[2]

        org.junit.Assert.assertEquals("Shift", row.keys.first().label)
        org.junit.Assert.assertEquals(
            listOf("z", "x", "c", "v", "b", "n", "m"),
            row.keys.subList(1, 8).map { it.label }
        )
        org.junit.Assert.assertEquals("Backspace", row.keys.last().label)
    }

    @org.junit.Test
    fun bottomRowContainsSymbolsSpaceAndEnter() {
        val layout = KeyboardLayout.alphabetic()

        val row = layout.rows[3]

        org.junit.Assert.assertEquals(
            listOf("?123", "Space", "Enter"),
            row.keys.map { it.label }
        )
    }

    @org.junit.Test
    fun letterKeysInsertTheirOwnLowercaseText() {
        val layout = KeyboardLayout.alphabetic()

        val letterKeys = layout.rows
            .flatMap { it.keys }
            .filter { it.action is KeyAction.InsertText }

        org.junit.Assert.assertEquals(26, letterKeys.size)

        letterKeys.forEach { key ->
            val action = key.action as KeyAction.InsertText
            org.junit.Assert.assertEquals(key.label, action.text)
        }
    }

    @org.junit.Test
    fun functionKeysHaveCorrectActions() {
        val layout = KeyboardLayout.alphabetic()

        val thirdRow = layout.rows[2]
        val bottomRow = layout.rows[3]

        org.junit.Assert.assertEquals(
            KeyAction.Shift,
            thirdRow.keys.first().action
        )

        org.junit.Assert.assertEquals(
            KeyAction.Backspace,
            thirdRow.keys.last().action
        )

        org.junit.Assert.assertEquals(
            KeyAction.Symbols,
            bottomRow.keys[0].action
        )

        org.junit.Assert.assertEquals(
            KeyAction.Space,
            bottomRow.keys[1].action
        )

        org.junit.Assert.assertEquals(
            KeyAction.Enter,
            bottomRow.keys[2].action
        )
    }

    @org.junit.Test
    fun alphabeticLayoutContainsExactly26LetterKeys() {
        val layout = KeyboardLayout.alphabetic()

        val letters = layout.rows
            .flatMap { it.keys }
            .filter { it.action is KeyAction.InsertText }
            .map { it.label }

        org.junit.Assert.assertEquals(26, letters.toSet().size)
        org.junit.Assert.assertEquals(
            ('a'..'z').map { it.toString() }.toSet(),
            letters.toSet()
        )
    }
}
