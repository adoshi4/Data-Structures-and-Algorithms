import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Test the Euler Problem 81 solution, by looking at the basic matrix
 * that yields a minimal path sum of 2427
 */
class TestEuler {
    @Test
    fun testEulerProblem81() {
        /**
         * Construct the matrix by using a list of lists
         */
        val matrix = listOf(
            listOf(131, 673, 234, 103, 18),
            listOf(201, 96, 342, 965, 150),
            listOf(630, 803, 746, 422, 111),
            listOf(537, 699, 497, 121, 956),
            listOf(805, 732, 524, 37, 331)
        )
        /**
         * Check if the minimal path sum returned is equal to the expected
         * minimal path sum
         */
        assertEquals(2427, solveEulerProblem81(matrix))
    }
}
