import kotlin.random.Random
import kotlin.system.measureTimeMillis
/**
 * Class that assesses the performance of the two matrix multiplication algorithms.
 * Computes the run time of Strassen and regular matrix multiplication.
 */
class MultiplicationPerformanceTest {
    /**
     * Compares the performance of naive and Strassen's matrix multiplication algorithms.
     * This method measures the time taken to multiply two matrices of the same size using
     * both algorithms and prints out the results for each matrix size in the provided list.
     * @param sizes A list of integers representing the sizes of the matrices to be tested.
     */
    fun comparePerformance(sizes: List<Int>) {
        sizes.forEach { size ->
            val matrix1 = Matrix.randomMatrix(size)
            val matrix2 = Matrix.randomMatrix(size)

            val naiveTime = measureTimeMillis {
                matrix1.naiveMultiply(matrix2)
            }

            val strassenTime = measureTimeMillis {
                matrix1.strassenMultiply(matrix2)
            }

            println("Matrix size: $size x $size")
            println("Naive multiplication time: $naiveTime ms")
            println("Strassen multiplication time: $strassenTime ms")
            println()
        }
    }
}
