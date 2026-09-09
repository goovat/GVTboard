package com.goovat.gvtboard.keyboard

class PrefixSuggestionSource(
    words: List<String>
) : SuggestionSource {

    private val words = words
        .filter { it.isNotBlank() }
        .distinctBy { it.lowercase() }

    override fun suggest(
        currentWord: String,
        context: TextEditingContext
    ): List<SuggestionCandidate> {
        if (currentWord.isEmpty()) {
            return emptyList()
        }

        val normalizedCurrentWord = currentWord.lowercase()

        return words
            .filter { word ->
                word.lowercase().startsWith(normalizedCurrentWord) &&
                    !word.equals(currentWord, ignoreCase = true)
            }
            .map { word ->
                SuggestionCandidate(
                    text = word,
                    score = score(word, currentWord)
                )
            }
    }

    private fun score(
        word: String,
        currentWord: String
    ): Double {
        val lengthDifference =
            word.length - currentWord.length

        return 1.0 / (1 + lengthDifference)
    }
}
