package com.goovat.gvtboard.keyboard

class TextCompositionService(
    private val controller: TextCompositionController,
    private val target: TextCompositionTarget
) {

    fun start(text: String): TextCompositionState {
        val state = controller.start(text)
        target.setComposingText(state.text)
        return state
    }

    fun update(text: String): TextCompositionState {
        val state = controller.update(text)
        target.setComposingText(state.text)
        return state
    }

    fun commit(text: String): TextCompositionState {
        target.commitText(text)
        return controller.clear()
    }

    fun finish(): TextCompositionState {
        target.finishComposingText()
        return controller.finish()
    }

    fun reset(): TextCompositionState {
        target.finishComposingText()
        return controller.clear()
    }
}
