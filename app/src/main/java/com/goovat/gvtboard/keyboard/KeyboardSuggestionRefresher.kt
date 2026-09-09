package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection

class KeyboardSuggestionRefresher {

    fun refresh(
        inputConnection: InputConnection
    ): SuggestionRowState {
        val contextTarget =
            InputConnectionEditingContextTarget(
                inputConnection
            )

        val contextService =
            TextEditingContextService(
                contextTarget
            )

        val currentWordService =
            CurrentWordService(
                contextService
            )

        val sourceRegistry =
            SuggestionSourceRegistry(
                listOf(
                    PrefixSuggestionSource(
                        DefaultSuggestionDictionary.words
                    )
                )
            )

        val suggestionService =
            SuggestionService(
                contextService = contextService,
                currentWordService = currentWordService,
                sourceRegistry = sourceRegistry
            )

        val suggestions =
            suggestionService.suggest()

        return SuggestionRowPolicy()
            .createState(suggestions)
    }
}
