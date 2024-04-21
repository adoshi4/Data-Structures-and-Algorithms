/**
 * Class representing a given node within a Binary Search Tree.
 * Contains a left and right child representing a value less than (left) and greater
 * than (right) the given node value.
 * @property value The value (integer) stored at the given node within
 * the tree
 */
class BinarySearchTree(var value: Int) {
    var left: BinarySearchTree? = null
    var right: BinarySearchTree? = null
}
