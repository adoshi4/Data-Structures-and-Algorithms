## Lempel-Ziv Results
In the LZWSampleStrings.kt file, I ran a set of five
test input strings on my Lempel-Ziv implementation. Based
on my analysis, I have found that strings that
are highly repetitive and larger tend to have higher compression
ratios.

Note: Run the main function to see the results of the
Lempel-Ziv algorithm inputs.

### Example - "AAAAAAAAAA"
This input string generated a compression ratio of
60%, because the repetition allowed for progressively
larger substrings to be encoded under the algorithm, and
compress the data more.

### Example - "1234567890"
This input string generated a compression ratio of 0%
because there is no repetition. That would mean that each
individual character is unique and will be encoded by itself.
