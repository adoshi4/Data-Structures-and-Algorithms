import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


/**
 * Unit tests for the Sorting class
 */
class SortingTest {

    private val sorting = Sorting()

    /**
     * Test the selection sort function to see if it correctly sorts
     * given lists - one randomly generated and another in reverse
     */
    @Test
    fun testSelectionSort() {
        val unsortedList = mutableListOf(5, 3, 8, 1, 2)
        val sortedList = sorting.selectionSort(unsortedList.toMutableList())
        val unsortedList2 = mutableListOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val sortedList2 = sorting.selectionSort(unsortedList2.toMutableList())
        assertEquals(listOf(1, 2, 3, 5, 8), sortedList)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), sortedList2)
    }

    /**
     * Test the quick sort function to see if it correctly sorts
     * given lists - one randomly generated and another in reverse
     */
    @Test
    fun testQuickSort() {
        val unsortedList = mutableListOf(5, 3, 8, 1, 2)
        val sortedList = sorting.selectionSort(unsortedList.toMutableList())
        val unsortedList2 = mutableListOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val sortedList2 = sorting.selectionSort(unsortedList2.toMutableList())
        assertEquals(listOf(1, 2, 3, 5, 8), sortedList)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), sortedList2)
    }

    /**
     * Test the merge sort function to see if it correctly sorts
     * given lists - one randomly generated and another in reverse
     */
    @Test
    fun testMergeSort() {
        val unsortedList = mutableListOf(5, 3, 8, 1, 2)
        val sortedList = sorting.mergeSort(unsortedList.toMutableList())
        val unsortedList2 = mutableListOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val sortedList2 = sorting.selectionSort(unsortedList2.toMutableList())
        assertEquals(listOf(1, 2, 3, 5, 8), sortedList)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), sortedList2)
    }

    /**
     * Test the insertion sort function to see if it correctly sorts
     * given lists - one randomly generated and another in reverse
     */
    @Test
    fun testInsertionSort() {
        val unsortedList = mutableListOf(5, 3, 8, 1, 2)
        val sortedList = sorting.insertionSort(unsortedList.toMutableList())
        val unsortedList2 = mutableListOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val sortedList2 = sorting.selectionSort(unsortedList2.toMutableList())
        assertEquals(listOf(1, 2, 3, 5, 8), sortedList)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), sortedList2)
    }
}
