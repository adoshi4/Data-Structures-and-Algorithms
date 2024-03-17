import kotlin.random.Random
/**
 * Class that has functions to build a matrix, and multiply two based
 * on a simple algorithm and the Strassen algorithm
 */
class Matrix(val size: Int) {
    val matrix: Array<Array<Double>> = Array(size) { Array(size) { 0.0 } }

    init {
        require(size > 0) { "Matrix size must be greater than 0" }
    }


    /**
     * Sets the value at the specified row and column in the matrix.
     * @param row the row index where the value should be set.
     * @param col the column index where the value should be set.
     * @param value the value to be set at the specified row and column.
     */
    fun setValue(row: Int, col: Int, value: Double) {
        require(row in 0 until size && col in 0 until size) { "Invalid row or column index" }
        matrix[row][col] = value
    }

    /**
     * Retrieves the value at the specified row and column in the matrix.
     * @param row the row index from which to get the value.
     * @param col the column index from which to get the value.
     * @return the value at the specified row and column.
     */
    fun getValue(row: Int, col: Int): Double {
        require(row in 0 until size && col in 0 until size) { "Invalid row or column index" }
        return matrix[row][col]
    }

    /**
     * Generates a string representation of the matrix for display purposes.
     * @return a string representation of the matrix.
     */
    override fun toString(): String {
        return matrix.joinToString("\n") { row ->
            row.joinToString("\t") { value -> "%.2f".format(value) }
        }
    }

    /**
     * Adds two matrices of the same size.
     * @param other the matrix to be added to this matrix.
     * @return the result of the addition as a new Matrix.
     */
    operator fun plus(other: Matrix): Matrix {
        require(this.size == other.size) { "Matrix sizes must be equal for addition" }
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j]
            }
        }
        return result
    }

    /**
     * Subtracts another matrix from this matrix
     * @param other the matrix to be subtracted from this matrix
     * @return the matrix result of the subtraction this matrix and the other matrix
     */
    operator fun minus(other: Matrix): Matrix {
        require(this.size == other.size) { "Matrix sizes must be equal for subtraction" }
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                result.matrix[i][j] = this.matrix[i][j] - other.matrix[i][j]
            }
        }
        return result
    }

    /**
     * Multiplies this matrix by another matrix using a simple multiplication algorithm.
     * @param other the matrix to multiply this matrix by.
     * @return the result of the multiplication as a new Matrix, or null if the sizes are incompatible.
     */
    fun multiply(other: Matrix): Matrix? {
        if (this.size != other.size) return null
        if (this.size == 1) {
            return Matrix(1).also { it.matrix[0][0] = this.matrix[0][0] * other.matrix[0][0] }
        }
        return strassenMultiply(other)
    }

    /**
     * Multiplies this matrix by another matrix using Strassen's algorithm.
     * @param other the matrix to multiply this matrix by.
     * @return the result of the multiplication as a new Matrix, or null if the sizes are incompatible.
     */
    fun strassenMultiply(other: Matrix, threshold: Int = 256): Matrix? {
        if (this.size != other.size) return null

        // Base case for 1x1 matrix
        if (this.size == 1) {
            return Matrix(1).also { it.matrix[0][0] = this.matrix[0][0] * other.matrix[0][0] }
        }

        // Use naive multiplication for small matrices.
        if (this.size <= threshold) {
            return this.naiveMultiply(other)
        }

        // Preparing matrices for Strassen's multiplication algorithm
        val newSize = this.size / 2
        val a11 = this.subMatrix(0, 0, newSize)
        val a12 = this.subMatrix(0, newSize, newSize)
        val a21 = this.subMatrix(newSize, 0, newSize)
        val a22 = this.subMatrix(newSize, newSize, newSize)

        val b11 = other.subMatrix(0, 0, newSize)
        val b12 = other.subMatrix(0, newSize, newSize)
        val b21 = other.subMatrix(newSize, 0, newSize)
        val b22 = other.subMatrix(newSize, newSize, newSize)

        // Computing the 7 products, recursively
        val p1 = (a11 + a22).strassenMultiply(b11 + b22)!!
        val p2 = (a21 + a22).strassenMultiply(b11)!!
        val p3 = a11.strassenMultiply(b12 - b22)!!
        val p4 = a22.strassenMultiply(b21 - b11)!!
        val p5 = (a11 + a12).strassenMultiply(b22)!!
        val p6 = (a21 - a11).strassenMultiply(b11 + b12)!!
        val p7 = (a12 - a22).strassenMultiply(b21 + b22)!!

        // Reconstructing the 4 quadrants of the result matrix
        val c11 = p1 + p4 - p5 + p7
        val c12 = p3 + p5
        val c21 = p2 + p4
        val c22 = p1 - p2 + p3 + p6

        // Combining the quadrants into a single result matrix
        return combine(c11, c12, c21, c22, newSize)
    }

    /**
     * Extracts a submatrix from the current matrix. Used as part of Strassen
     * Multiplication algorithm.
     * @param row The starting row index from which to extract the submatrix.
     * @param col The starting column index from which to extract the submatrix.
     * @param size The size of the submatrix to be extracted.
     * @return A new Matrix instance representing the submatrix of the specified size.
     */
    fun subMatrix(row: Int, col: Int, size: Int): Matrix {
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                result.matrix[i][j] = this.matrix[row + i][col + j]
            }
        }
        return result
    }

    /**
     * Combines four submatrices into a single larger matrix. This method is part
     * of the Strassen multiplication algorithm.
     * @param c11 The top-left submatrix.
     * @param c12 The top-right submatrix.
     * @param c21 The bottom-left submatrix.
     * @param c22 The bottom-right submatrix.
     * @param newSize The size of each submatrix, which is used to calculate the size of the combined matrix.
     * @return A new Matrix instance representing the combined matrix from the four submatrices.
     */
    fun combine(c11: Matrix, c12: Matrix, c21: Matrix, c22: Matrix, newSize: Int): Matrix {
        val result = Matrix(newSize * 2)
        for (i in 0 until newSize) {
            for (j in 0 until newSize) {
                result.matrix[i][j] = c11.matrix[i][j]
                result.matrix[i][j + newSize] = c12.matrix[i][j]
                result.matrix[i + newSize][j] = c21.matrix[i][j]
                result.matrix[i + newSize][j + newSize] = c22.matrix[i][j]
            }
        }
        return result
    }

    /**
     * Performs naive matrix multiplication with another matrix.
     * This method implements the standard matrix multiplication algorithm.
     * @param other The matrix to multiply with this matrix.
     * @return The result of the multiplication as a new Matrix instance.
     */
    fun naiveMultiply(other: Matrix): Matrix {
        require(this.size == other.size) { "Matrices must be of the same size to multiply" }

        val result = Matrix(this.size)
        for (i in 0 until this.size) {
            for (j in 0 until this.size) {
                var sum = 0.0
                for (k in 0 until this.size) {
                    sum += this.getValue(i, k) * other.getValue(k, j)
                }
                result.setValue(i, j, sum)
            }
        }
        return result
    }
    companion object {
        /**
         * Generates a matrix of a given size with random values.
         * @param size The size of the matrix to generate.
         * @return A new Matrix instance filled with random values.
         */
        fun randomMatrix(size: Int): Matrix {
            val matrix = Matrix(size)
            for (i in 0 until size) {
                for (j in 0 until size) {
                    matrix.setValue(i, j, Random.nextDouble(0.0, 10.0))
                }
            }
            return matrix
        }
    }
}
