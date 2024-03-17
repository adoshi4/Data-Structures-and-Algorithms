import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Unit tests for the Matrix class
 */
class TestMatrix {
    /**
     * Tests the ability to set and then retrieve a value at a specific location within the matrix.
     * Ensures that the setValue and getValue functions are correctly interacting with the matrix data.
     */
    @Test
    fun testSetValueAndGetValue() {
        val matrix = Matrix(2)
        matrix.setValue(0, 0, 5.0)
        assertEquals(5.0, matrix.getValue(0, 0))
    }

    /**
     * Tests the correctness of the simple matrix multiplication algorithm.
     * Ensures that multiplying two matrices produces the expected result.
     */
    @Test
    fun testMatrixMultiplication() {
        val matrix1 = Matrix(2).apply {
            setValue(0, 0, 1.0)
            setValue(0, 1, 2.0)
            setValue(1, 0, 3.0)
            setValue(1, 1, 4.0)
        }
        val matrix2 = Matrix(2).apply {
            setValue(0, 0, 2.0)
            setValue(0, 1, 0.0)
            setValue(1, 0, 1.0)
            setValue(1, 1, 2.0)
        }
        val expected = Matrix(2).apply {
            setValue(0, 0, 4.0)
            setValue(0, 1, 4.0)
            setValue(1, 0, 10.0)
            setValue(1, 1, 8.0)
        }
        val result = matrix1.multiply(matrix2)
        assertEquals(expected.toString(), result?.toString())
    }

    /**
     * Tests the correctness of the Strassen matrix multiplication algorithm.
     * Verifies that Strassen's algorithm yields the correct result when multiplying two matrices.
     */
    @Test
    fun testStrassenMultiplication() {
        val matrix1 = Matrix(2).apply {
            setValue(0, 0, 1.0)
            setValue(0, 1, 3.0)
            setValue(1, 0, 7.0)
            setValue(1, 1, 5.0)
        }
        val matrix2 = Matrix(2).apply {
            setValue(0, 0, 6.0)
            setValue(0, 1, 8.0)
            setValue(1, 0, 4.0)
            setValue(1, 1, 2.0)
        }
        val expectedStrassen = Matrix(2).apply {
            setValue(0, 0, 18.0)
            setValue(0, 1, 14.0)
            setValue(1, 0, 62.0)
            setValue(1, 1, 66.0)
        }
        val resultStrassen = matrix1.strassenMultiply(matrix2)
        assertEquals(expectedStrassen.toString(), resultStrassen?.toString(), "Strassen multiplication did not match expected result.")
    }
}
