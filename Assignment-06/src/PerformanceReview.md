## Shortcoming of K-Means
In the initial version of my code, I was simply
inputting the K-value manually, which was very inefficient
and did not produce the best results.
## Elbow Method
In order to address that shortcoming, I implemented the Elbow
Method. It calculates a within-cluster sum of squares metric,
which is the distance between the center and the data
in the cluster. The WCSS values for each K value are plotted
on a graph, and we choose the optimal K value accordingly.
## Sample Problems
The Elbow Method implementation is used to find the optimal K-value
for to solve a set of sample problems in the main function.
