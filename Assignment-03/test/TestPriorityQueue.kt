import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


/**
 * Unit tests for the PriorityQueue class
 */
class MinPriorityQueueTest {
    private lateinit var queue: MinPriorityQueue<String>

    /**
     * Initialize the priority queue to be used in the unit tests
     */
    @Before
    fun setUp() {
        queue = MinPriorityQueue()
    }

    /**
     * Test the addWithPriority and next functions in order to check if
     * elements correctly added to queue and if next identifying elements
     * at minimum priority level
     */
    @Test
    fun testAddWithPriorityAndNext() {
        /**
         * Add elements to the priority queue with assigned priority level
         */
        queue.addWithPriority("A", 5.0)
        queue.addWithPriority("B", 3.0)
        queue.addWithPriority("C", 7.0)

        /**
         * Check if the next function is correctly moving in order to min prioriyt
         * through the priority queue
         */
        assertEquals("B", queue.next())
        assertEquals("A", queue.next())
        assertEquals("C", queue.next())
    }

    /**
     * Check if adjust priority reassigns the priority level on individual
     * elements correctly
     */
    @Test
    fun testAdjustPriority() {
        /**
         * Add and assign priority to elements in priority queue
         */
        queue.addWithPriority("A", 5.0)
        queue.addWithPriority("B", 3.0)

        /**
         * Change priority of element "A" to 2.0
         */
        queue.adjustPriority("A", 2.0)

        assertEquals("A", queue.next())
        assertEquals("B", queue.next())
    }

    /**
     * Check if isEmpty() return true if empty and false otherwise
     */
    @Test
    fun testIsEmpty() {
        /**
         * Queue is empty at first, check if returning true
         */
        assertTrue(queue.isEmpty())

        /**
         * Queue no longer empty after element added, check if returning false
         */
        queue.addWithPriority("A", 5.0)
        assertTrue(!queue.isEmpty())
    }
}
