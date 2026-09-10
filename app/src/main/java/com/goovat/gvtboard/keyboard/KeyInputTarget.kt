package com.goovat.gvtboard.keyboard

interface KeyInputTarget {

    fun commitText(text: String)

    fun deleteBackward()

    fun sendEnter()

    fun moveCursorLeft()

    fun moveCursorRight()

    fun moveCursorUp()

    fun moveCursorDown()

    fun selectCurrentWord()

    fun selectAll()

    fun cut()

    fun copy()

    fun paste()

    fun undo()

    fun redo()
}
