# Strassen Multiplication
Based on my Performance Test, the Strassen
algorithm becomes faster at a matrix size of
64x64. This confirms the fact that Strassen tends
to be slower on smaller matrix sizes. I have created
a threshold variable, where any matrix below the threshold
size will use the regular multiplication algorithm
in order to speed up the process.
