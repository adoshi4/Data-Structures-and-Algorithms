import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
class TestQueue {

    /**
     * This is a basic test suite for the main functions of the [Queue] interface
     */
    @Test
    fun testEnqueue() {
        val queue = Queue<Int>()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)

        assertEquals(1, queue.peek())
    }

    @Test
    fun testDequeue() {
        val queue = Queue<String>()

        assertNull(queue.dequeue())

        queue.enqueue("A")
        queue.enqueue("B")
        queue.enqueue("C")

        assertEquals("A", queue.dequeue())
        assertEquals("B", queue.dequeue())
        assertEquals("C", queue.dequeue())
        assertNull(queue.dequeue())
    }

    @Test
    fun testPeek() {
        val queue = Queue<Double>()

        assertNull(queue.peek())

        queue.enqueue(3.14)
        queue.enqueue(2.71)

        assertEquals(3.14, queue.peek())
        assertEquals(3.14, queue.peek()) // Peek should not remove the element
    }

    @Test
    fun testIsEmpty() {
        val emptyQueue = Queue<Int>()
        assertTrue(emptyQueue.isEmpty())

        val nonEmptyQueue = Queue<String>()
        nonEmptyQueue.enqueue("One")
        assertFalse(nonEmptyQueue.isEmpty())
    }

}
