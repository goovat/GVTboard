package com.goovat.gvtboard.keyboard

class KeyActionExecutorTest {

    private class FakeInputTarget : KeyInputTarget {

        val committedTexts = mutableListOf<String>()
        var deleteBackwardCount = 0
        var enterCount = 0
        var cursorLeftCount = 0
        var cursorRightCount = 0
        var cursorUpCount = 0
        var cursorDownCount = 0

        override fun commitText(text: String) {
            committedTexts += text
        }

        override fun deleteBackward() {
            deleteBackwardCount++
        }

        override fun sendEnter() {
            enterCount++
        }

        override fun moveCursorLeft() {
            cursorLeftCount++
        }

        override fun moveCursorRight() {
            cursorRightCount++
        }

        override fun moveCursorUp() {
            cursorUpCount++
        }

        override fun moveCursorDown() {
            cursorDownCount++
        }
    }

    @org.junit.Test
    fun insertTextCommitsText() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.InsertText("hello"))

        org.junit.Assert.assertEquals(
            listOf("hello"),
            target.committedTexts
        )
    }

    @org.junit.Test
    fun uppercaseTextIsCommittedUnchanged() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.InsertText("Q"))

        org.junit.Assert.assertEquals(
            listOf("Q"),
            target.committedTexts
        )
    }

    @org.junit.Test
    fun spaceCommitsSingleSpace() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.Space)

        org.junit.Assert.assertEquals(
            listOf(" "),
            target.committedTexts
        )
    }

    @org.junit.Test
    fun backspaceDeletesBackward() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.Backspace)

        org.junit.Assert.assertEquals(
            1,
            target.deleteBackwardCount
        )
    }

    @org.junit.Test
    fun enterSendsEnter() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.Enter)

        org.junit.Assert.assertEquals(
            1,
            target.enterCount
        )
    }

    @org.junit.Test
    fun cursorNavigationMovesCursor() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.CursorLeft)
        executor.execute(KeyAction.CursorRight)
        executor.execute(KeyAction.CursorUp)
        executor.execute(KeyAction.CursorDown)

        org.junit.Assert.assertEquals(1, target.cursorLeftCount)
        org.junit.Assert.assertEquals(1, target.cursorRightCount)
        org.junit.Assert.assertEquals(1, target.cursorUpCount)
        org.junit.Assert.assertEquals(1, target.cursorDownCount)
    }

    @org.junit.Test
    fun shiftDoesNotModifyInputTarget() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.Shift)

        org.junit.Assert.assertTrue(target.committedTexts.isEmpty())
        org.junit.Assert.assertEquals(0, target.deleteBackwardCount)
        org.junit.Assert.assertEquals(0, target.enterCount)
    }

    @org.junit.Test
    fun symbolsDoesNotModifyInputTarget() {
        val target = FakeInputTarget()
        val executor = KeyActionExecutor(target)

        executor.execute(KeyAction.Symbols)

        org.junit.Assert.assertTrue(target.committedTexts.isEmpty())
        org.junit.Assert.assertEquals(0, target.deleteBackwardCount)
        org.junit.Assert.assertEquals(0, target.enterCount)
    }
}
