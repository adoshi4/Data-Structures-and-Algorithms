## Closest Element to Query Pseudocode

The goal of this is to find the closest element
along the tree to the query value. I would implement
a recursive algorithm as follows.

Here is my pseudocode:

//Function call

fun findClose (node, query) {

    // Base Case
    if node = null
        return null
    
    // Distance between given node and query value
    currDistance = distanceBetweenPoints (node.value, query)
    // In order to check which subtree to traverse
    if query < node.value
        distLeft = findClose(node.left, query)
        if distLeft < currDistance
            return distLeft
        else
            return currDistance
    else
        distRight = findClose(node.right, query)
        if distRight < currDistance
            return distRight
        else
            return currDistance
}

// Call the function on the root of the tree

findClose (tree root, query value)
