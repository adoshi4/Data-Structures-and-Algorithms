/**
 * Class that calculates the sum within a given range on a Binary Search Tree.
 */
class BSTRangeSum {
    var root: BinarySearchTree? = null

    /**
     * Calculate the sum of values of the nodes within the given range.
     * @property low the lower end of the range
     * @property high the higher end of the range
     * @returns the range sum with the given lower and upper bound for the BST.
     */
    fun rangeSum(low: Int, high: Int): Int {
        return rangeSumBST(root, low, high)
    }

    /**
     * Function that calculates the range sum recursively. Sum up the values in the given range.
     * @property node The given node in the tree
     * @property low lower bound of the range
     * @property high upper bound of the range
     */
    fun rangeSumBST(node: BinarySearchTree?, low: Int, high: Int): Int {
        if (node == null) return 0
        if (node.value > high) return rangeSumBST(node.left, low, high)
        if (node.value < low) return rangeSumBST(node.right, low, high)
        return node.value + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high)
    }
}
