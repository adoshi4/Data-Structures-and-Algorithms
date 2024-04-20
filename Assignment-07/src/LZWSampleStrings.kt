/**
 * Class that assesses the Lempel-Ziv algorithm's performance on multiple sample
 * input strings.
 */
class LZWSampleStrings {
    private val compressor = LZWCompress()

    /**
     * Lempel-Ziv compression on a set of sample strings. The output looks at the original string,
     * the encoded version, assesses the size before/after compression, and calculates the
     * compression ratio.
     */
    fun runDemo() {
        val samples = listOf(
            "TOBEORNOTTOBEORTOBEORNOT",
            "AAAAAAAAAA",
            "1234567890",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "abcdabcdabcdabcdabcdabcdabcdabcd"
        )

        samples.forEach { sample ->
            val encoded = compressor.encode(sample)
            val originalSize = sample.length * 8
            val compressedSize = encoded.size * (Math.log(compressor.maxCode.toDouble()) / Math.log(2.0)).toInt()
            val compressionRatio = 100 - (compressedSize.toDouble() / originalSize * 100)

            println("Original: $sample")
            println("Encoded: $encoded")
            println("Original Size (bits): $originalSize")
            println("Compressed Size (bits): $compressedSize")
            println("Compression Ratio: %.2f%%".format(compressionRatio))
            println()
        }
    }
}
