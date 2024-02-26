/**
 * Run Sorting Algorithm Benchmarking here
 */
fun main() {
    val sorting = Sorting()
    val listSizes = intArrayOf(100, 500, 1000, 5000, 10000)
    val repetitions = 5

    val mergeSortRuntimes = sorting.testSortingAlgorithm(sorting::mergeSort, listSizes, repetitions)
    val insertionSortRuntimes = sorting.testSortingAlgorithm(sorting::insertionSort, listSizes, repetitions)
    val quickSortRuntimes = sorting.testSortingAlgorithm(sorting::quickSort, listSizes, repetitions)
    val selectionSortRuntimes = sorting.testSortingAlgorithm(sorting::selectionSort, listSizes, repetitions)

    println("## Sorting Algorithm Performance Test\n")
    println("| List Size | Merge Sort (ms) | Insertion Sort (ms) | Quick Sort (ms) | Selection Sort (ms) |")
    println("|-----------|------------------|----------------------|")
    listSizes.forEach { size ->
        println("| $size | ${mergeSortRuntimes[size]} | ${insertionSortRuntimes[size]} | ${quickSortRuntimes[size]} | ${selectionSortRuntimes[size]} |")
    }
}
