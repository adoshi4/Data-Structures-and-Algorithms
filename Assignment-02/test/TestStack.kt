import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * This is a basic test suite for the main functions of the [Stack] interface
 */
class TestStack {
    @Test
    fun testPush() {
        val stack = Stack<Int>()
        stack.push(3)
        stack.push(5)
        stack.push(3)

        assertEquals(3, stack.peek())
    }

    @Test
    fun testPop() {
        val stack = Stack<String>()

        assertNull(stack.pop())

        stack.push("A")
        stack.push("B")
        stack.push("C")

        assertEquals("C", stack.pop())
        assertEquals("B", stack.pop())
        assertEquals("A", stack.pop())
        assertNull(stack.pop())
    }

    @Test
    fun testPeek() {
        val stack = Stack<Double>()

        assertNull(stack.peek())

        stack.push(3.14)
        stack.push(2.71)

        assertEquals(2.71, stack.peek())
        assertEquals(2.71, stack.peek()) // Peek should not remove the element
    }
    @Test
    fun testIsEmpty() {
        val emptyStack = Stack<Int>()
        assertTrue(emptyStack.isEmpty())

        val nonEmptyStack = Stack<String>()
        nonEmptyStack.push("One")
        assertFalse(nonEmptyStack.isEmpty())
    }
    @Test
    fun testReverseStack() {
        val stack = Stack<Int>()

        stack.push(1)
        stack.push(2)
        stack.push(3)

        reverseStack(stack)

        assertEquals(1, stack.pop())
        assertEquals(2, stack.pop())
        assertEquals(3, stack.pop())
        assertNull(stack.pop())
        assertTrue(stack.isEmpty())
    }

}
