package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection
import java.lang.reflect.Proxy

class InputConnectionCompositionTargetTest {

    private class FakeInputConnection {

        val composingTexts = mutableListOf<String>()
        val committedTexts = mutableListOf<String>()
        var composingCursorPosition: Int? = null
        var commitCursorPosition: Int? = null
        var finishComposingCount = 0

        val connection: InputConnection =
            Proxy.newProxyInstance(
                InputConnection::class.java.classLoader,
                arrayOf(InputConnection::class.java)
            ) { _, method, args ->

                when (method.name) {

                    "setComposingText" -> {
                        composingTexts += args?.get(0).toString()
                        composingCursorPosition =
                            args?.get(1) as? Int
                        true
                    }

                    "finishComposingText" -> {
                        finishComposingCount++
                        true
                    }

                    "commitText" -> {
                        committedTexts += args?.get(0).toString()
                        commitCursorPosition =
                            args?.get(1) as? Int
                        true
                    }

                    else -> {
                        when (method.returnType) {
                            Boolean::class.javaPrimitiveType -> false
                            Int::class.javaPrimitiveType -> 0
                            Long::class.javaPrimitiveType -> 0L
                            Float::class.javaPrimitiveType -> 0.0f
                            Double::class.javaPrimitiveType -> 0.0
                            else -> null
                        }
                    }
                }
            } as InputConnection
    }

    @org.junit.Test
    fun setComposingTextDelegatesWithCursorPositionOne() {
        val fake = FakeInputConnection()
        val target = InputConnectionCompositionTarget(fake.connection)

        target.setComposingText("hello")

        org.junit.Assert.assertEquals(
            listOf("hello"),
            fake.composingTexts
        )

        org.junit.Assert.assertEquals(
            1,
            fake.composingCursorPosition
        )
    }

    @org.junit.Test
    fun commitTextDelegatesWithCursorPositionOne() {
        val fake = FakeInputConnection()
        val target = InputConnectionCompositionTarget(fake.connection)

        target.commitText("hello")

        org.junit.Assert.assertEquals(
            listOf("hello"),
            fake.committedTexts
        )

        org.junit.Assert.assertEquals(
            1,
            fake.commitCursorPosition
        )
    }

    @org.junit.Test
    fun finishComposingTextDelegatesToInputConnection() {
        val fake = FakeInputConnection()
        val target = InputConnectionCompositionTarget(fake.connection)

        target.finishComposingText()

        org.junit.Assert.assertEquals(
            1,
            fake.finishComposingCount
        )
    }
}
