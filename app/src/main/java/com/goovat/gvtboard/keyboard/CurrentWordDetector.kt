package com.goovat.gvtboard.keyboard

class CurrentWordDetector(
    private val policy: CurrentWordPolicy = CurrentWordPolicy()
) {

    fun detect(context: TextEditingContext): String {
        if (context.selectedText.isNotEmpty()) {
            return ""
        }

        val textBeforeCursor = context.textBeforeCursor

        if (textBeforeCursor.isEmpty()) {
            return ""
        }

        var index = textBeforeCursor.lastIndex

        while (
            index >= 0 &&
            policy.isWordCharacter(textBeforeCursor[index])
        ) {
            index--
        }

        return textBeforeCursor.substring(index + 1)
    }
}
