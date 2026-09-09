package com.goovat.gvtboard.keyboard.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.SystemClock
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Button
import com.goovat.gvtboard.keyboard.KeyDefinition

class KeyboardKeyView(
    context: Context,
    private val keyDefinition: KeyDefinition,
    private val onKeyAction: (KeyDefinition) -> Unit,
    private val onLongPress: (KeyDefinition) -> Unit = {},
    private val isActive: Boolean = false,
    private val appearance: KeyboardKeyAppearance =
        KeyboardKeyAppearance(),
    private val longPressPolicy: KeyboardLongPressPolicy =
        KeyboardLongPressPolicy(),
    private val repeatPolicy: KeyboardLongPressRepeatPolicy =
        KeyboardLongPressRepeatPolicy(),
    private val repeatableKeyPolicy: KeyboardRepeatableKeyPolicy =
        KeyboardRepeatableKeyPolicy()
) : Button(context) {

    private val longPressDetector =
        KeyboardLongPressDetector(longPressPolicy)

    private val repeater =
        KeyboardLongPressRepeater(repeatPolicy)

    private val longPressRunnable = Runnable {
        val now = SystemClock.uptimeMillis()

        if (longPressDetector.check(now)) {
            onLongPress(keyDefinition)

            if (repeatableKeyPolicy.isRepeatable(keyDefinition)) {
                repeater.start(now)
                post(repeatRunnable)
            }
        }
    }

    private val repeatRunnable = object : Runnable {
        override fun run() {
            if (!repeater.isActive()) {
                return
            }

            val now = SystemClock.uptimeMillis()

            if (repeater.shouldRepeat(now)) {
                onKeyAction(keyDefinition)
            }

            if (repeater.isActive()) {
                postDelayed(
                    this,
                    repeatPolicy.repeatIntervalMs
                )
            }
        }
    }

    init {
        text = keyDefinition.label
        gravity = Gravity.CENTER
        isAllCaps = false
        textSize = 16f

        background = createBackground(
            if (isActive) {
                appearance.pressedFillColor()
            } else {
                appearance.normalFillColor()
            }
        )

        setOnTouchListener { _, event ->
            when (event.actionMasked) {

                MotionEvent.ACTION_DOWN -> {
                    background = createBackground(
                        appearance.pressedFillColor()
                    )

                    longPressDetector.pressDown(
                        SystemClock.uptimeMillis()
                    )

                    postDelayed(
                        longPressRunnable,
                        longPressPolicy.durationMs
                    )

                    true
                }

                MotionEvent.ACTION_UP -> {
                    removeCallbacks(longPressRunnable)
                    removeCallbacks(repeatRunnable)

                    val wasLongPress =
                        longPressDetector.check(
                            SystemClock.uptimeMillis()
                        )

                    longPressDetector.release()
                    repeater.stop()

                    background = createBackground(
                        appearance.normalFillColor()
                    )

                    if (!wasLongPress) {
                        performClick()
                    }

                    true
                }

                MotionEvent.ACTION_CANCEL -> {
                    removeCallbacks(longPressRunnable)
                    removeCallbacks(repeatRunnable)

                    longPressDetector.cancel()
                    repeater.stop()

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

    private fun createBackground(
        fillColor: Int
    ): GradientDrawable =
        GradientDrawable().apply {
            setColor(fillColor)
            setStroke(
                1,
                appearance.strokeColor()
            )
            cornerRadius =
                appearance.cornerRadiusPx()
        }
}
