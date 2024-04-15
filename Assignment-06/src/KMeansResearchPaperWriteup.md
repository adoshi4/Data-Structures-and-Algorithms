# X-Means Research Paper - Andrew Moore and Dan Pelleg

## Overview of K-Means
K-Means clustering is an Unsupervised Machine Learning
Algorithm. The goal with the Unsupervised
methods is to identify hidden patterns in data
without any predefined parameters. 

That is distinct from Supervised Learning, where there is some structure in
the data and the algorithm learns from a training set.
Based on the training set, the model will make predictions
on any subsequent test data.

Clustering refers to dividing data into groups of comparable
data. Each group has a center point. K refers to the number of
clusters/groups. K is usually an assigned value. This algorithm continuously
iterates, and the center is recalculated as data points are
reassigned to clusters. When no centers are recalculated, the iterations
stop.

## Shortcoming of K-Means
One of the main shortcomings of K-Means is assigning
a value of K. We do not have a predetermined
optimal value of K.

We generally utilize the Elbow Method to determine the K
value. It calculates a within-cluster sum of squares metric, 
which is the distance between the center and the data
in the cluster. The WCSS values for each K value are plotted
on a graph, and we choose the optimal K value accordingly.

## Alternative to the Elbow Method
This research paper outlined an alternative method for calculating
the optimal K value. Moore and Pelleg propose an "X-Means" method.
Instead of providing a single K value, the user can now provide
range of K values. Initially K will be set at the lower bound,
and then keep adding groups until hitting the upper bound. The configuration
with the best score (BIC) is chosen.

There are two core pieces to this algorithm. The first is the idea of
Improve Params, which means running K-Means to convergence. Then we want to 
emphasize the idea of Improve Structure, which entails understanding where the
new groups should appear. In the simulation run by the paper, there are initially
three different clusters, which are each split into two. The K-Means process is
then run on the sets of two clusters, and then the BIC is evaluated for one cluster
and two clusters. Based on the better score, either one or two clusters are retained.

## Sources
https://www.cs.cmu.edu/~dpelleg/download/xmeans.pdf
https://www.geeksforgeeks.org/elbow-method-for-optimal-value-of-k-in-kmeans/
