import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Unit tests for the NeedlemanWunsch class
 */
class TestNeedlemanWunsch {
    /**
     * Test the sequence alignment class by checking if the two given sequences
     * are correctly aligned by the function.
     */
    @Test
    fun testSequenceAlignment() {
        val nw = NeedlemanWunsch(-1.0, 2.0, -1.0) // Gap penalty: -1, Match score: 2, Mismatch score: -1
        val sequence1 = "AGTACGCA"
        val sequence2 = "TATGC"
        val expected = Pair("AGTACGCA", "--TATGC-")
        val result = nw.align(sequence1, sequence2)

        assertEquals(expected.first, result.first)
        assertEquals(expected.second, result.second)
    }
}
