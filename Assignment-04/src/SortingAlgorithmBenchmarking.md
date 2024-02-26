# Sorting Algorithm Benchmarking

I implemented the following sorting algorithms:

- Quick Sort
- Selection Sort
- Insertion Sort
- Merge Sort

## Running the Algorithm Benchmarking
The benchmarking code is in the main function. In order to see
the results, run the main function.

## Methodology

The algorithm test function is implemented in the same
class as the sorting algorithms. I generated multiple
test cases for each algorithm - completely randomized
lists, sorted lists, empty lists, and single-element lists.
There is a repetitions variable that represents the number
of times each algorithm was tested; the variable
is currently set to 5, for list sizes of 100, 500, 1000, 5000, 
and 10000. 

I also utilized a Kotlin library called measureTimeMillis, which
I came across through my own research. It essentially measures the
amount of time the code takes to run, and worked well for measuring
the time complexity of the sorting algorithms.

## Results

Based on my results, the Selection Sort and Insertion Sort algorithms
performed poorly on longer lists. They have a time complexity of O(n^2).
Quick Sort has a O(n^2) time complexity on certain inputs, but
generally performed at O(n log n). Merge Sort had the best
performance at O(n log n) - representing much more efficient sorting.
