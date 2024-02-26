# Adaptive Sorting

The research area I chose to explore is adaptive sorting.
There is active research area because it optimizes runtime
efficiency by dynamically changing the sorting algorithm.
The idea is that we should be able to change the sorting algorithm
based on the input, to get to the most optimal time complexity.

Solving adaptive sorting entails developing a program that will
be able to change the sorting algorithm based on the input.
That change in the algorithm would come with an understanding
of which sorting algorithm would display the highest runtime
efficiency with the given input and provide optimal performance.
This would be especially important when dealing with large datasets
where reducing runtime makes a substantial difference.

## Research Paper by Coester and Bai

### Key Idea

I read a paper called "Sorting with Predictions" by Xingjian Bai
and Christian Coester from the University of Oxford.
In the paper, they advocate for "learning-augmented" algorithms. The
idea is that we can utilize predictions about the input to improve sorting
time complexity.

The approach they took is to compare two "settings": One where they
predict the position of each item in the list and a second where they use a
"quick and dirty" methodology. They use these settings to prove that
the time complexity or number of comparisons is optimized when using predictions
rather than the "quick and dirty".

### Approach
In the first setting, they talk about prediction of an element's position
in a list. For example, they can use "empirical estimations of element distribution"
in order to derive those predictions.
In the second setting, they talk about the "quick and dirty" - 
a methodology of direct comparisons.

### Results/Conclusions
Their algorithm, the Double-Hoover sort, clearly provides a way
for us to utilize predictive analytics in order to optimize our
sorting processes. When dealing with large datasets in machine learning
or data mining context, having these capabilities can be invaluable. Based on
the data, the Double-Hoover sort had minimal comparisons compared to the others.
