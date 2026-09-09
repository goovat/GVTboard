package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection

class KeyboardSuggestionRefresher(
    private val sourceRegistry: SuggestionSourceRegistry =
        SuggestionSourceRegistry(
            listOf(
                PrefixSuggestionSource(
                    DefaultSuggestionDictionary.words
                )
            )
        ),
    private val rankingPolicy: SuggestionRankingPolicy =
        SuggestionRankingPolicy(),
    private val rowPolicy: SuggestionRowPolicy =
        SuggestionRowPolicy()
) {

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

        val suggestionService =
            SuggestionService(
                contextService = contextService,
                currentWordService = currentWordService,
                sourceRegistry = sourceRegistry,
                rankingPolicy = rankingPolicy
            )

        return rowPolicy.createState(
            suggestionService.suggest()
        )
    }
}
