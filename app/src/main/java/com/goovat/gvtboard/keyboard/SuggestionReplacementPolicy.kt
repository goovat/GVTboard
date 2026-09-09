package com.goovat.gvtboard.keyboard

class SuggestionReplacementPolicy {

    fun replacement(
        currentWord: String,
        suggestion: String
    ): String {
        if (currentWord.isEmpty() || suggestion.isEmpty()) {
            return suggestion
        }

        return when {
            currentWord.all { it.isUpperCase() } ->
                suggestion.uppercase()

            currentWord.first().isUpperCase() ->
                suggestion.replaceFirstChar { character ->
                    character.uppercase()
                }

            else ->
                suggestion
        }
    }
}
