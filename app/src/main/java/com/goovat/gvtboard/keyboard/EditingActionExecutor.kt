package com.goovat.gvtboard.keyboard

class EditingActionExecutor(
    private val inputTarget: KeyInputTarget
) {

    fun execute(action: EditingAction) {
        when (action) {
            EditingAction.CursorLeft ->
                inputTarget.moveCursorLeft()

            EditingAction.CursorRight ->
                inputTarget.moveCursorRight()

            EditingAction.CursorUp ->
                inputTarget.moveCursorUp()

            EditingAction.CursorDown ->
                inputTarget.moveCursorDown()

            EditingAction.Select ->
                inputTarget.selectCurrentWord()

            EditingAction.SelectAll ->
                inputTarget.selectAll()

            EditingAction.Cut ->
                inputTarget.cut()

            EditingAction.Copy ->
                inputTarget.copy()

            EditingAction.Paste ->
                inputTarget.paste()

            EditingAction.Undo ->
                inputTarget.undo()

            EditingAction.Redo ->
                inputTarget.redo()

            EditingAction.MoveToBeginning ->
                inputTarget.moveToBeginning()

            EditingAction.MoveToEnd ->
                inputTarget.moveToEnd()
        }
    }
}
