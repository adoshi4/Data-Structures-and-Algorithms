/**
 * Class that allows for the deletion of elements from a Binary Search Tree. Delete
 * the nodes based a value of node input.
 */
class BSTDelete {
    var root: BinarySearchTree? = null

    /**
     * Calls the recursive function that deletes nodes from the tree based on value.
     * @property value The value of the given node that will be removed from the tree.
     */
    fun delete(value: Int) {
        root = deleteRecursive(root, value)
    }

    /**
     * Function to find the node and delete it based on the input value. Look at several
     * different cases - nodes with two children, one child, and a leaf node.
     * @property current the node that is being evaluated for deletion (check for matching value)
     * @property value the value of the node to be deleted.
     */
    fun deleteRecursive(current: BinarySearchTree?, value: Int): BinarySearchTree? {
        current ?: return null

        when {
            value < current.value -> current.left = deleteRecursive(current.left, value)
            value > current.value -> current.right = deleteRecursive(current.right, value)
            else -> {
                if (current.left == null && current.right == null) {
                    return null
                }
                if (current.left == null) return current.right
                if (current.right == null) return current.left
                current.value = minValue(current.right!!)
                current.right = deleteRecursive(current.right, current.value)
            }
        }
        return current
    }

    /**
     * Function to find the minimum node value in the Binary Search Tree.
     * @property root root node of the tree that is being looked at.
     * @returns the minimum value in the tree.
     */
    private fun minValue(root: BinarySearchTree): Int {
        var minVal = root.value
        var current = root
        while (current.left != null) {
            minVal = current.left!!.value
            current = current.left!!
        }
        return minVal
    }
}
