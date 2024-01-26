import org.junit.Test
import kotlin.test.assertEquals

class PalindromeTest {
    @Test
    fun testNextPalindrome() {
        assertEquals(1331, nextPalindrome(1221))
        assertEquals(606, nextPalindrome(595))
        assertEquals(121, nextPalindrome(111))
    }
}
