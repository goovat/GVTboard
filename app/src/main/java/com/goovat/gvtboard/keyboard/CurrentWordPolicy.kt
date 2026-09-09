package com.goovat.gvtboard.keyboard

class CurrentWordPolicy {

    fun isWordCharacter(character: Char): Boolean =
        character.isLetterOrDigit() || character == '\''
}
