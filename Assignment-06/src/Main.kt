import kotlin.random.Random
import kotlin.math.cos
import kotlin.math.sin

/**
 * Various problems solved by the K-Means implementation.
 */
fun main() {
    val sampleProblems = listOf(
        List(50) { Point(Random.nextDouble(1.0, 5.0), Random.nextDouble(1.0, 5.0)) } +
                List(50) { Point(Random.nextDouble(10.0, 15.0), Random.nextDouble(10.0, 15.0)) },

        List(100) { Point(Random.nextDouble(0.0, 20.0), Random.nextDouble(0.0, 20.0)) },

        List(40) { Point(Random.nextDouble(0.0, 10.0), Random.nextDouble(0.0, 10.0)) } +
                List(40) { Point(Random.nextDouble(50.0, 60.0), Random.nextDouble(50.0, 60.0)) } +
                List(40) { Point(Random.nextDouble(25.0, 35.0), Random.nextDouble(25.0, 35.0)) },

        (1..20).flatMap { i ->
            List(10) { Point(Random.nextDouble(i*5.0, i*5.0+2.0), Random.nextDouble(i*5.0, i*5.0+2.0)) }
        },

        List(50) {
            val angle = Random.nextDouble(0.0, 2 * Math.PI)
            Point(5 * cos(angle) + 10, 5 * sin(angle) + 10)
        } + List(50) {
            val angle = Random.nextDouble(0.0, 2 * Math.PI)
            Point(10 * cos(angle) + 40, 10 * sin(angle) + 40)
        },

        List(40) { Point(Random.nextDouble(0.0, 20.0), Random.nextDouble(0.0, 20.0)) } +
                List(40) { Point(Random.nextDouble(80.0, 100.0), Random.nextDouble(80.0, 100.0)) } +
                List(5) { Point(Random.nextDouble(50.0, 60.0), Random.nextDouble(0.0, 100.0)) }  // Outliers
    )
    sampleProblems.forEachIndexed { index, points ->
        println("Testing Sample Problem #${index + 1} with ${points.size} points.")
        val elbowMethod = ElbowMethod(points)
        val distortions = elbowMethod.buildElbow()

        distortions.forEachIndexed { k, distortion ->
            println("k = ${k + 1}, Distortion: $distortion")
        }

        val optimalK = findElbowPoint(distortions)
        println("The optimal value of k for Problem #${index + 1} might be: $optimalK")
        
        val kMeans = KMeans(points, optimalK)
        val clusters = kMeans.cluster()

        clusters.forEach { (clusterIndex, clusterPoints) ->
            println("Cluster #$clusterIndex (${clusterPoints.size} points): Centroid = ${kMeans.centroids[clusterIndex]}")
        }
        println()
    }
}

fun findElbowPoint(distortions: List<Double>): Int {
    if (distortions.size < 3) return 1

    var maxDecreaseRate = 0.0
    var elbowIndex = 1

    for (i in 1 until distortions.size - 1) {
        val decreaseRate = distortions[i - 1] - distortions[i]
        val nextDecreaseRate = distortions[i] - distortions[i + 1]
        val rateChange = decreaseRate - nextDecreaseRate

        if (rateChange > maxDecreaseRate) {
            maxDecreaseRate = rateChange
            elbowIndex = i + 1
        }
    }

    return elbowIndex
}
