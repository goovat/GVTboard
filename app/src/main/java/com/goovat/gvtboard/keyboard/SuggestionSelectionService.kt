package com.goovat.gvtboard.keyboard

class SuggestionSelectionService(
    private val contextService: TextEditingContextService,
    private val currentWordService: CurrentWordService,
    private val target: SuggestionSelectionTarget,
    private val replacementPolicy: SuggestionReplacementPolicy =
        SuggestionReplacementPolicy()
) : SuggestionSelectionTargetService {

    override fun select(
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

        val replacement =
            replacementPolicy.replacement(
                currentWord = currentWord,
                suggestion = suggestion.text
            )

        target.replaceCurrentWord(
            currentWord = currentWord,
            replacement = replacement
        )

        return true
    }
}
