import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before

/**
 * Unit tests for the AssociativeArray class
 */
class AssociativeArrayTest {
    lateinit var associativeArray: AssociativeArray<String, Int>

    @Before
    fun setUp() {
        associativeArray = AssociativeArray()
    }

    /**
     * Check if the size returned by this function of the Associative array
     * is correct.
     */
    @Test
    fun testSize() {
        assertEquals(0, associativeArray.size())
        associativeArray["apple"] = 100
        associativeArray["banana"] = 200
        assertEquals(2, associativeArray.size())
        associativeArray.remove("apple")
        assertEquals(1, associativeArray.size())
    }

    /**
     * Test the set and get functions in the AssociateArray class.
     * See if values are pulled and set correctly.
     */
    @Test
    fun testSetAndGet() {
        associativeArray["apple"] = 100
        assertEquals(100, associativeArray["apple"])
        assertNull(associativeArray["banana"])
    }

    /**
     * Check if a given key is in the associative array.
     */
    @Test
    fun testContains() {
        assertFalse("apple" in associativeArray)
        associativeArray["apple"] = 100
        assertTrue("apple" in associativeArray)
    }

    /**
     * Test to see if any entries within the table can be removed.
     * Check based on the return value of the remove function.
     */
    @Test
    fun testRemove() {
        associativeArray["apple"] = 100
        assertTrue("apple" in associativeArray)
        assertTrue(associativeArray.remove("apple"))
        assertFalse("apple" in associativeArray)
        assertFalse(associativeArray.remove("apple"))
    }

    /**
     * Test if the all the pairs in the table are returned by the
     * function.
     */
    @Test
    fun testKeyValuePairs() {
        associativeArray["apple"] = 100
        associativeArray["banana"] = 200
        val pairs = associativeArray.keyValuePairs()
        assertTrue(pairs.contains(Pair("apple", 100)))
        assertTrue(pairs.contains(Pair("banana", 200)))
        assertEquals(2, pairs.size)
    }

    /**
     * Check if the function to clear the contents of the Associative Array
     * works correctly.
     */
    @Test
    fun testClear() {
        associativeArray["apple"] = 100
        associativeArray["banana"] = 200
        associativeArray.clear()
        assertEquals(0, associativeArray.size())
        assertFalse("apple" in associativeArray)
    }

    /**
     * Test the resize function to see if size increase correctly and see
     * if the table entries can still be accessed after the resize.
     */
    @Test
    fun testResize() {
        for (i in 1..10) {
            associativeArray["key$i"] = i * 10
        }
        assertEquals(10, associativeArray.size())
        for (i in 1..10) {
            assertEquals(i * 10, associativeArray["key$i"])
        }
    }
}
