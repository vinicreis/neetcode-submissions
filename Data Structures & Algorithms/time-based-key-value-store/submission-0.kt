/**
    Design a time-based key-value this structure that can store multiple values for the same key
    at different time stamps and retrieve the key's value at a certain timestamp.

    Implement the TimeMap class:

    TimeMap() Initializes the object of the data structure.
 */
class TimeMap() {
    private val data: HashMap<String, List<Pair<Int, String>>> = hashMapOf()

    /**
        Stores the key [key] with the value value at the given time timestamp.
     */
    fun set(key: String, value: String, timestamp: Int) {
        data.addTo(key, value, timestamp)

        // println(data)
    }

    /**
        Returns a value such that [set] was called previously, with timestamp_prev <= timestamp. 
        
        If there are multiple such values, it returns the value associated with the largest timestamp_prev. 
        
        If there are no values, it returns "".
     */
    fun get(key: String, timestamp: Int): String {
        // Time:  O(log n)
        // Space: O(1)
        return data[key]?.binarySearch(timestamp).orEmpty()
    }

    /*
        
     */
    private fun <K, V> HashMap<K, List<Pair<Int, V>>>.addTo(key: K, value: V, timestamp: Int) {
        // Time:  O(n log n), set by the sorting algorithm
        // Space: O(1)
        this[key] = (this[key] ?: listOf())
            .let { list -> list + (timestamp to value) }
            .let { it.sortedBy { it.first } } // Heapsort: O(n log n)
            // .also { println(it) }
    }

    private fun <T> List<Pair<Int, T>>.binarySearch(timestamp: Int): T? {
        // Time:  O(log n)
        // Space: O(1)

        if (isEmpty()) return null

        // println("List: $this")
        // println("Looking for timestamp: $timestamp")

        var result: T? = null
        var timestampPrev = timestamp
        var from = 0
        var to = lastIndex

        while (from <= to) { // O(log n)
            val index = from + (to - from) / 2
            val found = this[index]
            val (foundTimestamp, foundValue) = found

            when {
                foundTimestamp == timestamp -> return foundValue
                foundTimestamp > timestamp -> to = index - 1
                foundTimestamp < timestamp -> {
                    result = foundValue
                    from = index + 1
                }
            }
        }

        return result
    }
}
