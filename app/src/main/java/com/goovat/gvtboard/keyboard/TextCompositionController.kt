package com.goovat.gvtboard.keyboard

class TextCompositionController {

    var state: TextCompositionState = TextCompositionState()
        private set

    fun start(text: String): TextCompositionState {
        state = state.start(text)
        return state
    }

    fun update(text: String): TextCompositionState {
        state = state.update(text)
        return state
    }

    fun finish(): TextCompositionState {
        state = state.finish()
        return state
    }

    fun clear(): TextCompositionState {
        state = state.clear()
        return state
    }
}
