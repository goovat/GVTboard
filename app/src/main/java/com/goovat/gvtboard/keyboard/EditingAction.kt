package com.goovat.gvtboard.keyboard

sealed interface EditingAction {

    data object CursorLeft : EditingAction

    data object CursorRight : EditingAction

    data object CursorUp : EditingAction

    data object CursorDown : EditingAction

    data object Select : EditingAction

    data object SelectAll : EditingAction

    data object Cut : EditingAction

    data object Copy : EditingAction

    data object Paste : EditingAction

    data object Undo : EditingAction

    data object Redo : EditingAction

    data object MoveToBeginning : EditingAction

    data object MoveToEnd : EditingAction
}
