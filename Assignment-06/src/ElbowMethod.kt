/**
 * Implementation of the Elbow Method which is used to find the optimal value
 * of K in the K-Means algorithm. It utilizes distortion metrics for each of the
 * K values to find the optimal value.
 */
class ElbowMethod(val points: List<Point>) {
    /**
     * Calculate distortion metrics for a list of K values from 1 to 20. The distortion
     * metric is essentially an average distance to cluster center metric for each K value.
     * The goal is to find the point where the distortion has been reasonably minimized.
     * @returns A list of type double, with the average distortion for a set of K values.
     */
    fun buildElbow(): List<Double> {
        if (points.isEmpty()) return emptyList()  // Handle empty input

        val distortions = mutableListOf<Double>()
        for (k in 1 until 20) {
            val kMeans = KMeans(points, k)
            val clusters = kMeans.cluster()
            var totalDistance = 0.0

            clusters.forEach { (clusterIndex, clusterPoints) ->
                val centroid = kMeans.centroids[clusterIndex]
                clusterPoints.forEach { point ->
                    totalDistance += kMeans.distance(centroid, point)
                }
            }

            val averageDistortion = if (points.isNotEmpty()) totalDistance / points.size else 0.0
            distortions.add(averageDistortion)
        }
        return distortions
    }
}
