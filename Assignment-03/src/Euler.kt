import java.io.File

/**
 * Read integer matrix from a given text file and convert it to a list
 * of lists to represent the matrix
 * @param filename the given text file path from which matrix pulled
 * @property matrix the representation of the list of lists from file path
 */
fun readMatrixFromFile(filename: String): List<List<Int>> {
    val matrix = mutableListOf<List<Int>>()
    val FilePath = "src/matrix.txt"
    File(FilePath).forEachLine { line ->
        val row = line.split(",").map { it.toInt() }
        matrix.add(row)
    }
    return matrix
}

/**
 * Solves Prob. 81 using the matrix read in from the text file. Find the shortest
 * path between the top left and bottom right of the matrix.
 *
 * @param matrix The matrix of integers representing the weights of cells.
 * @return The minimal path sum from the top-left to the bottom-right cell, or null if no path exists.
 */
fun solveEulerProblem81(matrix: List<List<Int>>): Int? {
    val numRows = matrix.size
    val numCols = matrix[0].size
    val graph = Graph<Pair<Int, Int>>()

    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            graph.addEdge(Pair(i, j), Pair(i + 1, j), matrix[i][j].toDouble())
            graph.addEdge(Pair(i, j), Pair(i, j + 1), matrix[i][j].toDouble())
        }
    }

    val shortestPath = graph.djikstraPath(Pair(0, 0), Pair(numRows - 1, numCols - 1))

    return shortestPath?.sumBy { (x, y) -> matrix[x][y] }
}
