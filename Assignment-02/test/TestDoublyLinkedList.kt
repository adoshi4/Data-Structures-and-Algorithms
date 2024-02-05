import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * This is a test suite for the main functions of the [DoublyLinkedList] class
 */
class TestDoublyLinkedList {
    @Test
    fun testPushFront() {
        val list = DoublyLinkedList<Int>()
        list.pushFront(3)
        list.pushFront(5)
        list.pushFront(3)

        assertEquals(3, list.peekFront())
        assertEquals(3, list.peekBack())
        assertFalse(list.isEmpty())
    }

    @Test
    fun testPushBack() {
        val list = DoublyLinkedList<String>()
        list.pushBack("A")
        list.pushBack("B")
        list.pushBack("C")

        assertEquals("A", list.peekFront())
        assertEquals("C", list.peekBack())
        assertFalse(list.isEmpty())
    }

    @Test
    fun testPopFront() {
        val list = DoublyLinkedList<Int>()
        list.pushBack(3)
        list.pushBack(5)
        list.pushBack(3)

        assertEquals(3, list.popFront())
        assertEquals(5, list.popFront())
        assertEquals(3, list.popFront())
        assertNull(list.popFront())
        assertTrue(list.isEmpty())
    }

    @Test
    fun testPopBack() {
        val list = DoublyLinkedList<Char>()
        list.pushFront('A')
        list.pushFront('B')
        list.pushFront('C')

        assertEquals('A', list.popBack())
        assertEquals('B', list.popBack())
        assertEquals('C', list.popBack())
        assertNull(list.popBack())
    }

    @Test
    fun testPeekFront() {
        val list = DoublyLinkedList<String>()
        assertNull(list.peekFront())

        list.pushBack("Hello")
        assertEquals("Hello", list.peekFront())
    }

    @Test
    fun testPeekBack() {
        val list = DoublyLinkedList<Int>()
        assertNull(list.peekBack())

        list.pushBack(42)
        assertEquals(42, list.peekBack())
    }

    @Test
    fun testIsEmpty() {
        val list = DoublyLinkedList<Float>()
        assertTrue(list.isEmpty())

        list.pushFront(1.0f)
        assertFalse(list.isEmpty())

        list.popFront()
        assertTrue(list.isEmpty())
    }
}
