/**
 * Class that converts a sorted array to a Binary Search Tree. The root of the tree
 * is the middle of the sorted array.
 */
class ArrayToBST {
    /**
     * Function to convert the sorted array to a BST. The root is set to the middle
     * of the array. This balances the BST as well.
     * @property nums The input sorted array that will be converted
     * @returns the root node of the newly constructed Binary Search Tree.
     */
    fun sortedArrayToBST(nums: IntArray): BinarySearchTree? {
        if (nums.isEmpty()) {
            return null
        }
        return createBST(nums, 0, nums.size - 1)
    }

    /**
     * Function to construct the tree recursively.
     * @property nums the sorted array to be converted to a BST.
     * @property start starting index of the array
     * @property end ending index of the array
     * @returns The subtree of a given node in the BST.
     */
    fun createBST(nums: IntArray, start: Int, end: Int): BinarySearchTree? {
        if (start > end) {
            return null
        }
        val mid = start + (end - start) / 2
        val node = BinarySearchTree(nums[mid])
        node.left = createBST(nums, start, mid - 1)
        node.right = createBST(nums, mid + 1, end)
        return node
    }
}

/**
 * Function to print a given Binary Search Tree to the console. Provides a representation
 * of the tree to ensure that it functions correctly.
 * @property root The root node of the Binary Search Tree.
 * @property level Current level or depth on the tree
 * @returns Visual representation of the Binary Search Tree.
 */
fun printSideways(root: BinarySearchTree?, level: Int = 0) {
    if (root == null) {
        return
    }
    printSideways(root.right, level + 1)
    println(" ".repeat(4 * level) + root.value)
    printSideways(root.left, level + 1)
}
