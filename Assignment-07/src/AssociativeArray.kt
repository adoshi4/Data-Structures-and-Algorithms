import kotlin.math.abs

/**
 * Represents a simple associative array using hashing with separate chaining.
 * @property K the type of the keys.
 * @property V the type of the values.
 */
class AssociativeArray<K, V>(initialCapacity: Int = 256, val loadFactor: Float = 0.75f) {
    var table: Array<MutableList<Pair<K, V>>?> = arrayOfNulls(initialCapacity)
    var size = 0

    init {
        require(initialCapacity > 0) { "Initial capacity must be positive" }
        require(loadFactor > 0) { "Load factor must be positive" }
    }

    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     * @property K the type of the keys.
     * @property V the type of the values.
     */
    operator fun set(key: K, value: V) {
        val index = indexFor(key)
        var bucket = table[index]
        if (bucket == null) {
            bucket = mutableListOf()
            table[index] = bucket
        }
        val pairIndex = bucket.indexOfFirst { it.first == key }
        if (pairIndex != -1) {
            bucket[pairIndex] = key to value
        } else {
            bucket.add(key to value)
            size++
            if (size >= loadFactor * table.size) resize()
        }
    }

    /**
     * Check if a given key is in the associative array.
     * @property K the type of the keys.
     * @return true if [k] is a key in the associative array
     */
    operator fun contains(key: K): Boolean {
        return table[indexFor(key)]?.any { it.first == key } ?: false
    }

    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    operator fun get(key: K): V? = table[indexFor(key)]?.find { it.first == key }?.second

    /**
     * Remove the key, [k], from the associative array
     * @property K the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    fun remove(key: K): Boolean {
        val index = indexFor(key)
        val bucket = table[index] ?: return false
        val success = bucket.removeIf { it.first == key }
        if (success) {
            size--
            if (bucket.isEmpty()) table[index] = null
        }
        return success
    }

    /**
     * @return the number of elements stored in the hash table
     */
    fun size(): Int = size

    /**
     * @return the full list of key value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K, V>> {
        return table.filterNotNull().flatten()
    }

    /**
     * Find the hash index for the given key within the table.
     * @property K the type of the keys.
     * @returns Integer representing the index for the key
     */
    fun indexFor(key: K): Int = abs(key.hashCode() % table.size)

    /**
     * Increase the size of the hash table by a factor of two, then rehash
     * the entries.
     */
    fun resize() {
        val oldTable = table
        table = arrayOfNulls(table.size * 2)
        oldTable.forEach { bucket ->
            bucket?.forEach { (key, value) ->
                this[key] = value  // reinsert entries
            }
        }
    }

    /**
     * Remove all entries from the associative array.
     */
    fun clear() {
        table = arrayOfNulls(table.size)
        size = 0
    }
}
