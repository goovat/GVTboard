package com.goovat.gvtboard.keyboard

data class KeyboardState(
    val isShifted: Boolean = false,
    val isSymbols: Boolean = false
) {

    fun toggleShift(): KeyboardState =
        copy(isShifted = !isShifted)

    fun enableShift(): KeyboardState =
        copy(isShifted = true)

    fun disableShift(): KeyboardState =
        copy(isShifted = false)

    fun toggleSymbols(): KeyboardState =
        copy(isSymbols = !isSymbols)

    fun enableSymbols(): KeyboardState =
        copy(isSymbols = true)

    fun disableSymbols(): KeyboardState =
        copy(isSymbols = false)
}
