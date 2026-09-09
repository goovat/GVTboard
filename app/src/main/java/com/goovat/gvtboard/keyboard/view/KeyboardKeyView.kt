package com.goovat.gvtboard.keyboard.view

import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Button
import android.content.Context
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardKeyView(
    context: Context,
    private val keyDefinition: KeyDefinition,
    private val onKeyAction: (KeyDefinition) -> Unit,
    private val onLongPress: (KeyDefinition) -> Unit = {},
    private val appearance: KeyboardKeyAppearance =
        KeyboardKeyAppearance(),
    private val longPressPolicy: KeyboardLongPressPolicy =
        KeyboardLongPressPolicy()
) : Button(context) {

    private val longPressDetector =
        KeyboardLongPressDetector(longPressPolicy)

    private val longPressRunnable = Runnable {
        if (
            longPressDetector.check(
                android.os.SystemClock.uptimeMillis()
            )
        ) {
            onLongPress(keyDefinition)
        }
    }

    init {
        text = keyDefinition.label
        gravity = Gravity.CENTER
        isAllCaps = false
        textSize = 16f

        background = createBackground(
            appearance.normalFillColor()
        )

        setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    background = createBackground(
                        appearance.pressedFillColor()
                    )

                    longPressDetector.pressDown(
                        android.os.SystemClock.uptimeMillis()
                    )

                    postDelayed(
                        longPressRunnable,
                        longPressPolicy.durationMs
                    )

                    true
                }

                MotionEvent.ACTION_UP -> {
                    removeCallbacks(longPressRunnable)

                    val pressEvent = longPressDetector.release()

                    background = createBackground(
                        appearance.normalFillColor()
                    )

                    if (pressEvent == KeyboardPressEvent.Tap) {
                        performClick()
                    }

                    true
                }

                MotionEvent.ACTION_CANCEL -> {
                    removeCallbacks(longPressRunnable)
                    longPressDetector.cancel()

                    background = createBackground(
                        appearance.normalFillColor()
                    )

                    true
                }

                else -> true
            }
        }

        setOnClickListener {
            onKeyAction(keyDefinition)
        }
    }

    private fun createBackground(fillColor: Int): GradientDrawable =
        GradientDrawable().apply {
            setColor(fillColor)
            setStroke(1, appearance.strokeColor())
            cornerRadius = appearance.cornerRadiusPx()
        }
}
