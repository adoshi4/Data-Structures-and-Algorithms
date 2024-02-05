/**
 * Create a mutable list using an array as the
 * underlying data structure
 * @property array: represents the mutable list
 * @property size: represents the size of the list, since
 * arrays have fixed size
 */

class MyMutableIntList {
    var array = IntArray(1)
    var size = 0
    /**
     * Add [element] to the end of the list
     */
    fun add(element: Int){

        if (size >= array.size) {
            val tempArray = IntArray(array.size*2)
            for (i in 0..<size){
                tempArray[i] = array[i]
            }
            array = tempArray
        }
        array[size] = element
        size++

    }

    /**
     * Remove all elements from the list
     */
    fun clear() {
        val tempArray = IntArray(0)
        array = tempArray
    }

    /*
     * @return the size of the list
     */
    fun size(): Int {
        return array.size
    }

    /**
     * @param index the index to return
     * @return the element at [index]
     */
    operator fun get(index: Int):Int {
        return array[index]
    }

    /**
     * Store [value] at position [index]
     * @param index the index to set
     * @param value to store at [index]
     */
    operator fun set(index: Int, value: int) {
        array[index] = value
    }
}
