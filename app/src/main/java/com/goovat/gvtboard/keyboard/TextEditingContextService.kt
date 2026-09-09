package com.goovat.gvtboard.keyboard

class TextEditingContextService(
    private val target: TextEditingContextTarget,
    private val policy: TextEditingContextPolicy =
        TextEditingContextPolicy()
) {

    fun read(): TextEditingContext =
        target.readContext(
            maxBeforeCursorChars = policy.maxBeforeCursorChars,
            maxSelectedChars = policy.maxSelectedChars,
            maxAfterCursorChars = policy.maxAfterCursorChars
        )
}
