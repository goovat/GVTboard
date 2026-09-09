package com.goovat.gvtboard.keyboard

data class TextCompositionState(
    val text: String = "",
    val isComposing: Boolean = false
) {

    fun start(composingText: String): TextCompositionState =
        copy(
            text = composingText,
            isComposing = true
        )

    fun update(composingText: String): TextCompositionState =
        copy(
            text = composingText,
            isComposing = true
        )

    fun finish(): TextCompositionState =
        copy(
            isComposing = false
        )

    fun clear(): TextCompositionState =
        TextCompositionState()
}
