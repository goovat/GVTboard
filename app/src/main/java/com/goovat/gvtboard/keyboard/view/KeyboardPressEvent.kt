package com.goovat.gvtboard.keyboard.view

sealed interface KeyboardPressEvent {

    data object Tap : KeyboardPressEvent

    data object LongPress : KeyboardPressEvent
}
