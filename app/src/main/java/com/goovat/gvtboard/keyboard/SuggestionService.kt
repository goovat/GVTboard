package com.goovat.gvtboard.keyboard

class SuggestionService(
    private val contextService: TextEditingContextService,
    private val currentWordService: CurrentWordService,
    private val sourceRegistry: SuggestionSourceRegistry,
    private val rankingPolicy: SuggestionRankingPolicy =
        SuggestionRankingPolicy()
) {

    fun suggest(): List<SuggestionCandidate> {
        val context = contextService.read()
        val currentWord = currentWordService.readCurrentWord(context)

        val candidates = sourceRegistry.suggest(
            currentWord = currentWord,
            context = context
        )

        return rankingPolicy.rank(candidates)
    }
}
