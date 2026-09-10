package com.goovat.gvtboard.keyboard

data class SymbolLayout(
    val rows: List<KeyboardRow>
) {

    companion object {

        fun standard(): SymbolLayout =
            SymbolLayout(
                rows = listOf(
                    KeyboardRow(
                        keys = "!@#$%^&*()".map { symbol ->
                            KeyDefinition(
                                label = symbol.toString(),
                                action = KeyAction.InsertText(symbol.toString())
                            )
                        }
                    ),
                    KeyboardRow(
                        keys = "-_+=/\\|~".map { symbol ->
                            KeyDefinition(
                                label = symbol.toString(),
                                action = KeyAction.InsertText(symbol.toString())
                            )
                        }
                    ),
                    KeyboardRow(
                        keys = listOf(
                            "[", "]", "{", "}", "<", ">",
                            ":", ";", "'", "\"", ",", ".", "?"
                        ).map { symbol ->
                            KeyDefinition(
                                label = symbol,
                                action = KeyAction.InsertText(symbol)
                            )
                        }
                    )
                )
            )
    }
}
