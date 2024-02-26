/**
 * An implementation the Insertion, Quick, Selection, and Merge sort algorithms
 */

import kotlin.system.measureTimeMillis
import kotlin.random.Random

/**
 * Class with all sorting algorithm functions
 */
class Sorting{
    /**
     * Implementation of a selection sort, sorting method that repeatedly
     * collects the smallest element in the unsorted portion of the list
     * and swaps with the beginning of the list
     * @property sortList the list data of type Int, that needs to be sorted
     * @returns the result of sorting list by selection sort
     */
    fun selectionSort (sortList: MutableList<Int>): List<Int>{
        for (i in sortList.indices) {
            var minIndex = i
            for (k in i + 1 until sortList.size) {
                if (sortList[k] < sortList[minIndex]){
                    minIndex = k
                }
            }
            if (minIndex != i) {
                val temp = sortList[i]
                sortList[i] = sortList[minIndex]
                sortList[minIndex] = temp
            }
        }
        return sortList.toList()
    }

    /**
     * Implementation of a quick sort, sorting method that finds a pivot point
     * and all smaller elements to left of the pivot and larger to the right
     * @property sortList the list data of type Int, that needs to be sorted
     * @returns the result of sorting list by quick sort
     */
    fun quickSort (sortList:MutableList<Int>): List<Int> {
        if (sortList.size <= 1) {
            return sortList.toList()
        }
        val pivot = sortList[sortList.size - 1]
        var leftList = mutableListOf<Int>()
        var equalList = mutableListOf<Int>()
        var rightList = mutableListOf<Int>()
        for (item in sortList) {
            when {
                item < pivot -> leftList.add(item)
                item == pivot -> equalList.add(item)
                item > pivot -> rightList.add(item)
            }
        }
        return quickSort(leftList).plus(equalList).plus(quickSort(rightList))
    }

    /**
     * Implementation of merge sort, sorting method that repeatedly
     * splits the list in half and merges back together while sortng
     * @property sortList the list data of type Int, that needs to be sorted
     * @returns the result of sorting list by merge sort
     */
    fun mergeSort (sortList: MutableList<Int>): List<Int>{
        if (sortList.size > 1) {
            val middle = sortList.size / 2
            val leftList = sortList.subList(0, middle).toMutableList()
            val rightList = sortList.subList(middle, sortList.size).toMutableList()

            // Recursively sort the left and right halves
            mergeSort(leftList)
            mergeSort(rightList)

            var i = 0
            var j = 0
            var k = 0

            // Merge the sorted halves into sortList
            while (i < leftList.size && j < rightList.size) {
                if (leftList[i] < rightList[j]) {
                    sortList[k++] = leftList[i++]
                } else {
                    sortList[k++] = rightList[j++]
                }
            }

            // Copy remaining elements from leftList
            while (i < leftList.size) {
                sortList[k++] = leftList[i++]
            }

            // Copy remaining elements from rightList
            while (j < rightList.size) {
                sortList[k++] = rightList[j++]
            }
        }
        return sortList
    }

    /**
     * Implementation of a insertion sort, splitting the list into
     * sorted and unsorted portion, and then repeatedly swapping individual
     * elements until they are in the correct position
     * @property sortList the list data of type Int, that needs to be sorted
     * @returns the result of sorting list by insertion sort
     */
    fun insertionSort (sortList: MutableList<Int>): List<Int>{
        for (i in 1 until sortList.size) {
            var j = i
            while (j > 0 && sortList[j] < sortList[j - 1]) {
                // Swap elements if they are in the wrong order
                val temp = sortList[j]
                sortList[j] = sortList[j - 1]
                sortList[j - 1] = temp
                j--
            }
        }
        return sortList.toList()
    }

    /**
     * Implementation of a random list generation, which is used in the
     * testSortingAlgorithm method to assess the run time of each of the sorting
     * algorithms
     * @property size size of the list of random number
     * @returns randomly generated list to be used to assess sorting algorithms
     */
    fun generateRandomList(size: Int): MutableList<Int> {
        // Generates a list of random numbers of given size
        return MutableList(size) { Random.nextInt(1000) }
    }

    /**
     * Implementation that assesses the sorting algorithms given a randomly
     * generated list
     * @property sortAlgorithm the function of the given sorting algorithm that
     * returns the sorted list
     * @property listSizes array of integers representing the different sized lists
     * for which we are testing the run time for the sorting algorithms
     * @property repetitions how many times a given test is repeated to get the run time
     * @returns the average run time of the given sorting algorithm
     */
    fun testSortingAlgorithm(sortAlgorithm: (MutableList<Int>) -> List<Int>, listSizes: IntArray, repetitions: Int): Map<Int, Long> {
        // Tests the sorting algorithm on lists of different sizes and returns the runtimes

        val results = mutableMapOf<Int, Long>()

        for (size in listSizes) {
            var totalTime = 0L

            repeat(repetitions) {
                val list = generateRandomList(size)
                totalTime += measureTimeMillis {
                    sortAlgorithm(list)
                }
            }

            val averageTime = totalTime / repetitions
            results[size] = averageTime
        }

        return results
    }
}
