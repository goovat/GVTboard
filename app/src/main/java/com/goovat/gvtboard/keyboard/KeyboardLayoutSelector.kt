package com.goovat.gvtboard.keyboard

class KeyboardLayoutSelector {

    fun select(state: KeyboardState): List<KeyboardRow> =
        if (state.isSymbols) {
            SymbolLayout.standard().rows
        } else {
            KeyboardLayout.alphabetic(
                isShifted = state.isShifted,
                isCapsLocked = state.isCapsLocked
            ).rows
        }
}
