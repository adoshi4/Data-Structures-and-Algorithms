/**
 * Class that converts the Binary Search Tree into a structure where all nodes only
 * have a right child.
 */
class BSTIncreasingOrder {
    private var prev: BinarySearchTree? = null

    /**
     * Convert the BST into the increasing order BST.
     * @property root root node of the given BST.
     * @returns the root node of the new increasing order BST.
     */
    fun increaseOrder(root: BinarySearchTree?): BinarySearchTree? {
        val dummy = BinarySearchTree(0)
        prev = dummy

        inOrderTraversal(root)

        return dummy.right
    }

    /**
     * Function that runs through the BST and reorders the nodes to become
     * an increasing order tree. Done recursively, at the end the nodes only have a
     * right child.
     * @property node current node in the run through of the BST.
     */
    fun inOrderTraversal(node: BinarySearchTree?) {
        if (node == null) return

        inOrderTraversal(node.left)

        prev?.right = node
        node.left = null
        prev = node

        inOrderTraversal(node.right)
    }
}
