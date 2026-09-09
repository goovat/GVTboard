package com.goovat.gvtboard.keyboard

class CurrentWordMatcher(
    private val policy: CurrentWordPolicy = CurrentWordPolicy()
) {

    fun match(context: TextEditingContext): CurrentWordMatch? {
        if (context.selectedText.isNotEmpty()) {
            return null
        }

        val before = context.textBeforeCursor
        val after = context.textAfterCursor

        var beforeIndex = before.lastIndex

        while (
            beforeIndex >= 0 &&
            policy.isWordCharacter(before[beforeIndex])
        ) {
            beforeIndex--
        }

        var afterIndex = 0

        while (
            afterIndex < after.length &&
            policy.isWordCharacter(after[afterIndex])
        ) {
            afterIndex++
        }

        val beforeWord =
            before.substring(beforeIndex + 1)

        val afterWord =
            after.substring(0, afterIndex)

        val word =
            beforeWord + afterWord

        if (word.isEmpty()) {
            return null
        }

        return CurrentWordMatch(
            text = word,
            charactersBeforeCursor = beforeWord.length,
            charactersAfterCursor = afterWord.length
        )
    }
}
