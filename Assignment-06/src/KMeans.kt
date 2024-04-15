import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

data class Point(val x: Double, val y: Double)

/**
 * Class implementing the K-Means clustering algorithm
 */
class KMeans(val points: List<Point>, val k: Int) {
    var centroids: MutableList<Point> = mutableListOf()

    init {
        initializeCentroids()
    }

    /**
     * Randomly assigns a set of centroids at the beginning of the K-Means process,
     * this serves as the basis for reassigning clusters and recalculating the center
     * point.
     */
    fun initializeCentroids() {
        centroids = points.shuffled().take(k).toMutableList()
    }

    /**
     * Calculate the Euclidean distance between two given points. This is used
     * to assign to clusters.
     * @property p1 The first point, this is of type Point.
     * @property p2 The second point, this is of type Point.
     * @returns the resulting Euclidean distance between two given points.
     */
    fun distance(p1: Point, p2: Point): Double {
        return sqrt((p2.x - p1.x).pow(2) + (p2.y - p1.y).pow(2))
    }

    /**
     * Assign a given point to a cluster based on the Euclidean distance
     * from the cluster center.
     * @property point which is the point that needs to be assigned, of type Point.
     */
    fun assignToCluster(point: Point): Int =
        centroids.indices.minByOrNull { distance(point, centroids[it]) } ?: -1

    /**
     * Update the centroids of each cluster after points have been reassigned.
     * This happens on every K-Means iteration.
     * @property clusters represented by a Map, which includes a list of points in each cluster.
     */
    fun updateCentroids(clusters: Map<Int, List<Point>>) {
        for ((index, clusterPoints) in clusters) {
            if (clusterPoints.isNotEmpty()) {
                centroids[index] = Point(
                    clusterPoints.map { it.x }.average(),
                    clusterPoints.map { it.y }.average()
                )
            }
        }
    }

    /**
     * The core function, continuously move through the list of points in each cluster
     * and do the reassigning process until no points are changing clusters, in which
     * case convergence has been reached.
     * @returns clusters represented by a Map along with the corresponding points in
     * each cluster.
     */
    fun cluster(): Map<Int, List<Point>> {
        var clusters = emptyMap<Int, List<Point>>()
        var prevClusters: Map<Int, List<Point>>

        do {
            prevClusters = clusters
            clusters = points.groupBy { assignToCluster(it) }
            updateCentroids(clusters)
        } while (!isConverged(clusters, prevClusters))

        return clusters
    }

    /**
     * Check if iterative K-Means process has concluded based on the point at which
     * zero points change clusters.
     * @property current current configuration of clusters.
     * @property previous previous configuration of clusters.
     * @returns boolean value, return true if the two configurations are the same, else false
     */
    fun isConverged(current: Map<Int, List<Point>>, previous: Map<Int, List<Point>>): Boolean =
        current.keys.all { key -> current[key] == previous[key] }

    /**
     * Finds the cluster that a given point belongs to.
     * @property point the point for which the cluster must be found.
     * @returns center of the cluster that the point belongs to.
     */
    fun getCentroidForPoint(point: Point): Point =
        centroids[assignToCluster(point)]
}
