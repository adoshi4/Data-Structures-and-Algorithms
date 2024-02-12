import org.example.MinHeap

/**
 * This is a simple ``Graph`` that represents a directed graph
 * @param VertexType the representation of a vertex in the graph
 * @property graphList the representation of the
 */
class Graph<VertexType> {

    val graphList: MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()
    /**
     * @return the vertices in the graph
     */
    fun getVertices(): Set<VertexType> {
        return graphList.keys
    }

    /**
     * Add an edge from a given vertex to another, with a specified "weight" stored
     * in the cost variable
     * @param from the initial vertex
     * @param to the end vertex
     * @param cost represents the "weight" or cost of moving from one vertex
     * to the next
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        val additionalEdge = graphList.getOrPut(from){mutableMapOf()}
        additionalEdge[to] = cost
    }

    /**
     * Find all edges associated with a given vertex
     * @param the given vertex
     * @return all edges for a given vertex, return type is a Map since
     * we also want to record the associated cost with each end vertex
     */
    fun getEdges(from: VertexType): Map<VertexType, Double> {
        return graphList[from] ?: emptyMap()
    }

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear() {
        graphList.clear()
    }

    /**
     * Find the shortest path between two nodes using Djikstra's algorithm
     * which finds the "cheapest" path based on cost
     * @param start given starting vertex
     * @param destination the end vertex, find path from start vertex
     * @return A list representing the nodes in the shortest path based on
     * Djikstra's algorithm
     */
    fun djikstraPath(start: VertexType, destination: VertexType): List<VertexType>? {
        val distances = mutableMapOf<VertexType, Double>()
        val predecessors = mutableMapOf<VertexType, VertexType>()
        val pq = MinHeap<VertexType>()

        // In Djikstra's algorithm, every node is initialized to infinity aside from
        // the start node
        for (vertex in getVertices()) {
            distances[vertex] = Double.POSITIVE_INFINITY
        }
        distances[start] = 0.0

        pq.insert(start, 0.0)

        while (!pq.isEmpty()) {
            val current = pq.getMin() ?: break
            val distance = distances[current] ?: continue

            for ((neighbor, edgeWeight) in getEdges(current)) {
                val newDistance = distance + edgeWeight

                if (newDistance < (distances[neighbor] ?: Double.POSITIVE_INFINITY)) {
                    distances[neighbor] = newDistance
                    predecessors[neighbor] = current
                    pq.insert(neighbor, newDistance)
                }
            }
        }


        val shortestPath = mutableListOf<VertexType>()
        var current = destination
        while (current != start) {
            shortestPath.add(current)
            current = predecessors[current] ?: return null // No path exists
        }
        shortestPath.add(start)
        shortestPath.reverse()

        return shortestPath
    }
}

/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 *  @property priorQueue representation of the priority queue using MinHeap implementation
 */
class MinPriorityQueue<T> {

    var priorQueue: MinHeap<T> = MinHeap()
    /**
     * Check if our priority queue is empty
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean {
        return priorQueue.isEmpty()
    }

    /**
     * Add [elem] with at level [priority]
     * @param elem the element we are adding to the priority queue
     * @param priority the associate priority with that element
     */
    fun addWithPriority(elem: T, priority: Double) {
        priorQueue.insert(elem, priority)
    }

    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    fun next(): T? {
        return priorQueue?.getMin()
    }

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double) {
        priorQueue.adjustHeapNumber(elem, newPriority)
    }
}
