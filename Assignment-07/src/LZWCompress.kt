
/**
 * Class that implements the Lempel-Ziv Data Compression algorithm.
 */
class LZWCompress {
    val dictionary = AssociativeArray<String, Int>()
    var maxCode = 255

    init {
        resetDictionary()
    }

    /**
     * Reset the dictionary to the base state, and each individual character is set
     * to a code value between 0 and 255. This is how the compression process starts.
     */
    fun resetDictionary() {
        dictionary.clear()
        for (i in 0..255) {
            dictionary[i.toChar().toString()] = i
        }
        maxCode = 255
    }

    /**
     * Encode an input string based on the Lempel-Ziv algorithm.
     * @property input The string that will be encoded.
     * @returns a list representing the integer encoding of the input string.
     */
    fun encode(input: String): List<Int> {
        resetDictionary()
        val result = mutableListOf<Int>()
        var w = ""

        input.forEach { c ->
            val wc = w + c
            if (dictionary.contains(wc)) {
                w = wc
            } else {
                result.add(dictionary[w]!!)
                dictionary[wc] = ++maxCode
                w = c.toString()
            }
        }

        if (w.isNotEmpty()) result.add(dictionary[w]!!)
        return result
    }
}
