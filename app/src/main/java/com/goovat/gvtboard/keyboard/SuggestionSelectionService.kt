package com.goovat.gvtboard.keyboard

class SuggestionSelectionService(
    private val contextService: TextEditingContextService,
    private val currentWordService: CurrentWordService,
    private val target: SuggestionSelectionTarget
) {

    fun select(
        suggestion: SuggestionCandidate
    ): Boolean {
        val context = contextService.read()

        if (context.selectedText.isNotEmpty()) {
            return false
        }

        val currentWord =
            currentWordService.readCurrentWord(context)

        if (currentWord.isEmpty()) {
            return false
        }

        target.replaceCurrentWord(
            currentWord = currentWord,
            replacement = suggestion.text
        )

        return true
    }
}
