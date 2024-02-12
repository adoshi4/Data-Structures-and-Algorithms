import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the Graph class
 */
class GraphTest {
    private lateinit var graph: Graph<Int>

    /**
     * Initialize graph to be used in unit tests
     */
    @Before
    fun setUp() {
        graph = Graph()
    }

    /**
     * Test both the add and get edge functions
     */
    @Test
    fun testAddEdgeAndGetEdges() {
        /**
         * Add edges with a specific cost
         */
        graph.addEdge(1, 2, 5.0)
        graph.addEdge(1, 3, 3.0)

        /**
         * Check if all edges from the newly added edge
         * are being pulled by the getEdge function
         */
        val edgesFrom1 = graph.getEdges(1)
        assertEquals(5.0, edgesFrom1[2])
        assertEquals(3.0, edgesFrom1[3])

        val edgesFrom2 = graph.getEdges(2)
        assertTrue(edgesFrom2.isEmpty())
    }

    /**
     * Check if contents of Graph being cleared by clear()
     */
    @Test
    fun testClear() {
        /**
         * Add edges to the graph and then call the clear() function to check
         * if graph is empty after clearing
         */
        graph.addEdge(1, 2, 5.0)
        graph.addEdge(1, 3, 3.0)

        graph.clear()

        assertTrue(graph.getVertices().isEmpty())
    }

    /**
     * Test if shortest path is found by Djikstra function
     */
    @Test
    fun testDjikstraPath() {
        /**
         * Create a graph, check if Djikstra function finding the shortest path
         * between Node 1 and 4
         */
        val graph = Graph<Int>()
        graph.addEdge(1, 2, 1.0)
        graph.addEdge(1, 3, 2.0)
        graph.addEdge(2, 3, 1.0)
        graph.addEdge(2, 4, 3.0)
        graph.addEdge(3, 4, 1.0)

        val shortestPath = graph.djikstraPath(1, 4)

        assertEquals(listOf(1, 3, 4), shortestPath)
    }

}
