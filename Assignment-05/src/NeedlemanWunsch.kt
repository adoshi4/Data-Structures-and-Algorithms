/**
 * Class that implements the Needleman Wunsch algorithm sequence alignment.
 */
class NeedlemanWunsch(val gapPenalty: Double, val matchScore: Double, val mismatchScore: Double) {

    /**
    * Aligns two sequences using the Needleman-Wunsch algorithm.
    * @param sequence1 The first sequence to be aligned.
    * @param sequence2 The second sequence to be aligned.
    * @return A pair of strings representing the optimal alignment of the two sequences.
    */
    fun align(sequence1: String, sequence2: String): Pair<String, String> {
        // Determine the larger size for the square matrix to accommodate both sequences
        val maxLen = maxOf(sequence1.length, sequence2.length) + 1
        val matrix = Matrix(maxLen) // Using the original Matrix class constructor

        // Initialize the first row and first column of the matrix with gap penalties
        for (i in 1 until maxLen) {
            matrix.setValue(i, 0, i * gapPenalty)
            matrix.setValue(0, i, i * gapPenalty)
        }

        // Fill in the matrix using dynamic programming
        for (i in 1..sequence1.length) {
            for (j in 1..sequence2.length) {
                val match = if (sequence1[i - 1] == sequence2[j - 1]) matchScore else mismatchScore
                val diagonal = matrix.getValue(i - 1, j - 1) + match
                val up = matrix.getValue(i - 1, j) + gapPenalty
                val left = matrix.getValue(i, j - 1) + gapPenalty
                matrix.setValue(i, j, maxOf(diagonal, up, left))
            }
        }

        // Traceback to find the alignment
        var alignment1 = ""
        var alignment2 = ""
        var i = sequence1.length
        var j = sequence2.length
        while (i > 0 || j > 0) {
            when {
                i > 0 && j > 0 && sequence1[i - 1] == sequence2[j - 1] -> {
                    alignment1 = sequence1[i - 1] + alignment1
                    alignment2 = sequence2[j - 1] + alignment2
                    i--
                    j--
                }
                i > 0 && (j == 0 || matrix.getValue(i, j) == matrix.getValue(i - 1, j) + gapPenalty) -> {
                    alignment1 = sequence1[i - 1] + alignment1
                    alignment2 = "-" + alignment2
                    i--
                }
                j > 0 && (i == 0 || matrix.getValue(i, j) == matrix.getValue(i, j - 1) + gapPenalty) -> {
                    alignment1 = "-" + alignment1
                    alignment2 = sequence2[j - 1] + alignment2
                    j--
                }
                else -> { // Correctly handle matching and mismatching cases
                    if (i > 0 && j > 0) {
                        alignment1 = sequence1[i - 1] + alignment1
                        alignment2 = sequence2[j - 1] + alignment2
                    } else if (i > 0) {
                        alignment1 = sequence1[i - 1] + alignment1
                        alignment2 = "-" + alignment2
                    } else if (j > 0) {
                        alignment1 = "-" + alignment1
                        alignment2 = sequence2[j - 1] + alignment2
                    }
                    i--
                    j--
                }
            }
        }

        return Pair(alignment1, alignment2)
    }
}
