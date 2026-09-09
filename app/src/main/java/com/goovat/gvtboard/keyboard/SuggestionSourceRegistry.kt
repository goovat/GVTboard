package com.goovat.gvtboard.keyboard

class SuggestionSourceRegistry(
    sources: List<SuggestionSource> = emptyList()
) {

    private val sources = sources.toList()

    fun suggest(
        currentWord: String,
        context: TextEditingContext
    ): List<SuggestionCandidate> =
        sources.flatMap { source ->
            source.suggest(
                currentWord = currentWord,
                context = context
            )
        }
}
