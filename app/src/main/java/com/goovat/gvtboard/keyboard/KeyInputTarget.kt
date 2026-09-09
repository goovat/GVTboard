package com.goovat.gvtboard.keyboard

interface KeyInputTarget {

    fun commitText(text: String)

    fun deleteBackward()

    fun sendEnter()
}
