package com.goovat.gvtboard.keyboard

class KeyboardActionDispatcher(
    private val controller: KeyboardController,
    private val executor: KeyActionExecutor
) {

    fun dispatch(action: KeyAction): KeyEventResult {
        val result = controller.handle(action)
        executor.execute(result.action)
        return result
    }
}
