package com.goovat.gvtboard.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.TextView

class GVTboardInputMethodService : InputMethodService() {

    override fun onCreateInputView(): View {
        return TextView(this).apply {
            text = "GVTboard"
            textSize = 24f
            setPadding(32, 32, 32, 32)
        }
    }
}
