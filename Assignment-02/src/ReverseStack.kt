/**
 *
 * @param stack data stored in the stack
 * @property reversedStack The reversed version of the given stack
 * data structure
 */
fun <T> reverseStack(stack: Stack<T>){
    val reversedStack = Stack<T>()

    while (!stack.isEmpty()) {
        val element = stack.pop()
        if (element != null) {
            reversedStack.push(element)
        }
    }
    stack.stack = reversedStack.stack
}
