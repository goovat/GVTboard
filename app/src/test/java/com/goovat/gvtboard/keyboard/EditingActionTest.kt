package com.goovat.gvtboard.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class EditingActionTest {

    @Test
    fun editingActionContainsExpectedToolbarOperations() {
        val actions =
            listOf(
                EditingAction.CursorLeft,
                EditingAction.CursorRight,
                EditingAction.CursorUp,
                EditingAction.CursorDown,
                EditingAction.Select,
                EditingAction.SelectAll,
                EditingAction.Cut,
                EditingAction.Copy,
                EditingAction.Paste,
                EditingAction.Undo,
                EditingAction.Redo,
                EditingAction.MoveToBeginning,
                EditingAction.MoveToEnd
            )

        assertEquals(13, actions.size)
    }
}
