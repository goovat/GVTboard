package com.goovat.gvtboard.keyboard

interface TextCompositionTarget {

    fun setComposingText(text: String)

    fun finishComposingText()

    fun commitText(text: String)
}
