package com.goovat.gvtboard.keyboard

import android.view.inputmethod.InputConnection
import java.lang.reflect.Proxy

class InputConnectionEditingContextTargetTest {

    private class FakeInputConnection(
        private val beforeCursor: CharSequence? = null,
        private val selected: CharSequence? = null,
        private val afterCursor: CharSequence? = null
    ) {

        val beforeCursorRequests = mutableListOf<Int>()
        val afterCursorRequests = mutableListOf<Int>()

        val connection: InputConnection =
            Proxy.newProxyInstance(
                InputConnection::class.java.classLoader,
                arrayOf(InputConnection::class.java)
            ) { _, method, args ->

                when (method.name) {
                    "getTextBeforeCursor" -> {
                        beforeCursorRequests += args?.get(0) as Int
                        beforeCursor
                    }

                    "getSelectedText" -> selected

                    "getTextAfterCursor" -> {
                        afterCursorRequests += args?.get(0) as Int
                        afterCursor
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
    fun readsTextAroundCursorAndSelection() {
        val fake = FakeInputConnection(
            beforeCursor = "hello ",
            selected = "world",
            afterCursor = "!"
        )

        val target = InputConnectionEditingContextTarget(fake.connection)

        val context = target.readContext(
            maxBeforeCursorChars = 64,
            maxSelectedChars = 32,
            maxAfterCursorChars = 64
        )

        org.junit.Assert.assertEquals(
            "hello ",
            context.textBeforeCursor
        )

        org.junit.Assert.assertEquals(
            "world",
            context.selectedText
        )

        org.junit.Assert.assertEquals(
            "!",
            context.textAfterCursor
        )

        org.junit.Assert.assertEquals(
            listOf(64),
            fake.beforeCursorRequests
        )

        org.junit.Assert.assertEquals(
            listOf(64),
            fake.afterCursorRequests
        )
    }

    @org.junit.Test
    fun selectedTextIsLimitedToRequestedLength() {
        val fake = FakeInputConnection(
            selected = "abcdefghij"
        )

        val target = InputConnectionEditingContextTarget(fake.connection)

        val context = target.readContext(
            maxBeforeCursorChars = 64,
            maxSelectedChars = 4,
            maxAfterCursorChars = 64
        )

        org.junit.Assert.assertEquals(
            "abcd",
            context.selectedText
        )
    }

    @org.junit.Test
    fun nullInputConnectionResultsBecomeEmptyStrings() {
        val fake = FakeInputConnection()

        val target = InputConnectionEditingContextTarget(fake.connection)

        val context = target.readContext(
            maxBeforeCursorChars = 64,
            maxSelectedChars = 32,
            maxAfterCursorChars = 64
        )

        org.junit.Assert.assertEquals(
            "",
            context.textBeforeCursor
        )

        org.junit.Assert.assertEquals(
            "",
            context.selectedText
        )

        org.junit.Assert.assertEquals(
            "",
            context.textAfterCursor
        )
    }
}
