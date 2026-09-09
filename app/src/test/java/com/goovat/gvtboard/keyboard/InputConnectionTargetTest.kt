package com.goovat.gvtboard.keyboard

import android.view.KeyEvent
import android.view.inputmethod.InputConnection
import java.lang.reflect.Proxy

class InputConnectionTargetTest {

    private class FakeInputConnection {

        val committedTexts = mutableListOf<String>()
        var codePointDeleteCount = 0
        var fallbackDeleteCount = 0
        val keyEvents = mutableListOf<KeyEvent>()

        val connection: InputConnection =
            Proxy.newProxyInstance(
                InputConnection::class.java.classLoader,
                arrayOf(InputConnection::class.java)
            ) { _, method, args ->

                when (method.name) {
                    "commitText" -> {
                        committedTexts += args?.get(0).toString()
                        true
                    }

                    "deleteSurroundingTextInCodePoints" -> {
                        codePointDeleteCount++
                        true
                    }

                    "deleteSurroundingText" -> {
                        fallbackDeleteCount++
                        true
                    }

                    "sendKeyEvent" -> {
                        keyEvents += args?.get(0) as KeyEvent
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
    fun commitTextUsesInputConnection() {
        val fake = FakeInputConnection()
        val target = InputConnectionTarget(fake.connection)

        target.commitText("hello")

        org.junit.Assert.assertEquals(
            listOf("hello"),
            fake.committedTexts
        )
    }

    @org.junit.Test
    fun commitTextPreservesUppercaseText() {
        val fake = FakeInputConnection()
        val target = InputConnectionTarget(fake.connection)

        target.commitText("Q")

        org.junit.Assert.assertEquals(
            listOf("Q"),
            fake.committedTexts
        )
    }

    @org.junit.Test
    fun deleteBackwardUsesCodePointDeletion() {
        val fake = FakeInputConnection()
        val target = InputConnectionTarget(fake.connection)

        target.deleteBackward()

        org.junit.Assert.assertEquals(
            1,
            fake.codePointDeleteCount
        )

        org.junit.Assert.assertEquals(
            0,
            fake.fallbackDeleteCount
        )
    }

    @org.junit.Test
    fun enterSendsDownAndUpEvents() {
        val fake = FakeInputConnection()
        val target = InputConnectionTarget(fake.connection)

        target.sendEnter()

        org.junit.Assert.assertEquals(
            2,
            fake.keyEvents.size
        )

        org.junit.Assert.assertNotNull(fake.keyEvents[0])
        org.junit.Assert.assertNotNull(fake.keyEvents[1])
    }
}
