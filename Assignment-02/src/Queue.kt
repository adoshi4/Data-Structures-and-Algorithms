/**
 * An implementation of a Queue using the underlying Doubly
 * Linked List implementation
 * @param T data stored in the stack
 * @property stack A stack with the Doubly Linked List underlying
 * data structure
 */

class Queue<T> {
    var queue: DoublyLinkedList<T>? = DoublyLinkedList()
    /**
     * Add [data] to the end of the queue.
     */
    fun enqueue(data: T){
        queue?.pushBack(data)
    }
    /**
     * Remove the element at the front of the queue.  If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */

    fun dequeue(): T? {
        return queue?.popFront()
    }
    /**
     * @return the value at the front of the queue or nil if none exists
     */
    fun peek(): T?{
        return queue?.peekFront()
    }
    /**
     * @return true if the queue is empty and false otherwise
     */
    fun isEmpty(): Boolean{
        return queue!!.isEmpty()
    }
}
