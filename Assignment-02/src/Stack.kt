/**
 * An implementation of a Stack using the underlying Doubly
 * Linked List implementation
 * @param T data stored in the stack
 * @property stack A stack with the Doubly Linked List underlying
 * data structure
 */
class Stack<T> {
    /**
     * Add [data] to the top of the stack
     */
    var stack: DoublyLinkedList<T>? = DoublyLinkedList()
    fun push(data: T) {
        stack?.pushFront(data)
    }
    /**
     * Remove the element at the top of the stack.  If the stack is empty, it remains unchanged.
     * @return the value at the top of the stack or nil if none exists
     */
    fun pop(): T? {
        return stack?.popFront()
    }
    /**
     * @return the value on the top of the stack or nil if none exists
     */
    fun peek(): T? {
        return stack?.peekFront()
    }
    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return stack!!.isEmpty()
    }
    /**
     * @parameter stack the data stored in the stack to be reversed
     * @return the reverse of a given stack
     */

}
