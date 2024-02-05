/**
 * An implementation of a Doubly Linked List
 * @param T data stored in the stack
 * @property head references the top element in the stack
 * @property tail references the back element in the stack
 */

/**
 * @param T the type of data to store
 * @property data the data of type T associated with the node
 * @property  next the next node in our Stack
 * @property  previous the previous node in our Stack
 */
class Node <T>(val data: T, var next: Node<T>? = null, var previous: Node<T>? = null){

}

class DoublyLinkedList <T>{
    internal var head: Node<T>?=null
    internal var tail: Node<T>?=null

    /**
     * Adds the element [data] to the front of the linked list.
     * @property data the data of type T associated with the node
     */
    fun pushFront(data: T) {
        val newNode = Node(data)
        if (isEmpty()){
            head = newNode
            tail = newNode
        }
        else{
            newNode.next = head
            head?.previous = newNode
            head = newNode
        }
    }
    /**
     * Adds the element [data] to the back of the linked list.
     * @property data the data of type T associated with the node
     */
    fun pushBack(data: T) {
        val newNode = Node(data)
        if(isEmpty()) {
            head = newNode
            tail = newNode
        }
        else {
            newNode.previous = tail
            tail?.next = newNode
            tail = newNode
        }
    }
    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T? {
        if(isEmpty()){
            return null
        }
        else {
            val frontNode = head?.data
            head = head?.next
            head?.previous = null
            return frontNode
        }
    }
    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T? {
        if(isEmpty()){
            return null
        }
        else {
            val backNode = tail?.data
            tail = tail?.previous
            tail?.next = null
            return backNode
        }
    }
    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T? {
        return head?.data
    }

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T? {
        return tail?.data
    }

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return head == null
    }
}
