package com.goovat.gvtboard.keyboard

data class KeyboardLayout(
    val rows: List<KeyboardRow>
) {

    companion object {

        fun alphabetic(isShifted: Boolean = false): KeyboardLayout {
            val letters = if (isShifted) {
                "QWERTYUIOP"
            } else {
                "qwertyuiop"
            }

            val homeLetters = if (isShifted) {
                "ASDFGHJKL"
            } else {
                "asdfghjkl"
            }

            val bottomLetters = if (isShifted) {
                "ZXCVBNM"
            } else {
                "zxcvbnm"
            }

            return KeyboardLayout(
                rows = listOf(
                    KeyboardRow(
                        keys = letters.map { letter ->
                            KeyDefinition(
                                label = letter.toString(),
                                action = KeyAction.InsertText(letter.toString())
                            )
                        }
                    ),
                    KeyboardRow(
                        keys = homeLetters.map { letter ->
                            KeyDefinition(
                                label = letter.toString(),
                                action = KeyAction.InsertText(letter.toString())
                            )
                        }
                    ),
                    KeyboardRow(
                        keys = buildList {
                            add(
                                KeyDefinition(
                                    label = "Shift",
                                    action = KeyAction.Shift
                                )
                            )

                            addAll(
                                bottomLetters.map { letter ->
                                    KeyDefinition(
                                        label = letter.toString(),
                                        action = KeyAction.InsertText(letter.toString())
                                    )
                                }
                            )

                            add(
                                KeyDefinition(
                                    label = "Backspace",
                                    action = KeyAction.Backspace
                                )
                            )
                        }
                    ),
                    KeyboardRow(
                        keys = listOf(
                            KeyDefinition(
                                label = "?123",
                                action = KeyAction.Symbols
                            ),
                            KeyDefinition(
                                label = "Space",
                                action = KeyAction.Space
                            ),
                            KeyDefinition(
                                label = "Enter",
                                action = KeyAction.Enter
                            )
                        )
                    )
                )
            )
        }
    }
}
