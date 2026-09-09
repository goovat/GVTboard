package com.goovat.gvtboard.keyboard

data class KeyboardState(
    val isShifted: Boolean = false,
    val isCapsLocked: Boolean = false,
    val isSymbols: Boolean = false
) {

    fun toggleShift(): KeyboardState =
        copy(isShifted = !isShifted)

    fun enableShift(): KeyboardState =
        copy(isShifted = true)

    fun disableShift(): KeyboardState =
        copy(isShifted = false)

    fun toggleCapsLock(): KeyboardState =
        copy(isCapsLocked = !isCapsLocked)

    fun enableCapsLock(): KeyboardState =
        copy(isCapsLocked = true)

    fun disableCapsLock(): KeyboardState =
        copy(isCapsLocked = false)

    fun toggleSymbols(): KeyboardState =
        copy(isSymbols = !isSymbols)

    fun enableSymbols(): KeyboardState =
        copy(isSymbols = true)

    fun disableSymbols(): KeyboardState =
        copy(isSymbols = false)
}
