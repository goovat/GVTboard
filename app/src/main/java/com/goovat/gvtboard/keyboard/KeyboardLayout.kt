package com.goovat.gvtboard.keyboard

data class KeyboardLayout(
    val rows: List<KeyboardRow>
) {

    companion object {

        fun alphabetic(): KeyboardLayout =
            KeyboardLayout(
                rows = listOf(
                    KeyboardRow(
                        keys = "qwertyuiop".map { letter ->
                            KeyDefinition(
                                label = letter.toString(),
                                action = KeyAction.InsertText(letter.toString())
                            )
                        }
                    ),
                    KeyboardRow(
                        keys = "asdfghjkl".map { letter ->
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
                                "zxcvbnm".map { letter ->
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
