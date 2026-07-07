/**

    Implement the Least Recently Used (LRU) cache class LRUCache. 
    
    The class should support the following operations.

    LRUCache(int capacity) Initialize the LRU cache of size capacity.

    Ensure that get and put each run in O(1) average time complexity.

 */

private data class Node(
    val key: Int,
    val value: Int,
    var prev: Node? = null,
    var next: Node? = null,
)

class LRUCache(capacity: Int) {
    private val capacity = capacity
    private val data = HashMap<Int, Node>()
    private val left = Node(0, 0)
    private val right = Node(0, 0)

    init {
        left.next = right
        right.prev = left
    }

    /**
        Return the value corresponding to the key if the key exists, otherwise return -1.
     */
    fun get(key: Int): Int {
        return data[key]?.let { node ->
            remove(node)
            add(node)

            node.value
        } ?: -1
    }

    /**

        Update the value of the key if the key exists.
        Otherwise, add the key-value pair to the cache.
        If the introduction of the new pair causes the cache to exceed its capacity, remove the least recently used key.
        A key is considered used if a get or a put operation is called on it.

     */
    fun put(key: Int, value: Int) {
        data[key]?.also { node ->
            remove(node)
            data.remove(node.key)
        }

        val node = Node(key, value)

        data[key] = node
        add(node)

        if (data.size > capacity) {
            left.next?.also { lru ->
                remove(lru)
                data.remove(lru.key)
            }
        }
    }

    private fun remove(node: Node) {
        val prev = node.prev
        val next = node.next
        prev?.next = next
        next?.prev = prev
    }

    private fun add(node: Node) {
        val prev = right.prev
        val next = right
        prev?.next = node
        node.prev = prev
        node.next = next
        next?.prev = node
    }
}
