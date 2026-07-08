class Solution {
    /**

        Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. 
        
        If such a substring does not exist, return an empty string "".

        You may assume that the correct output is always unique.

     */
    fun minWindow(s: String, t: String): String {
        return slidingWindow(s, t)
    }

    /**

        s       = "abcdefghid"
        t       = "cdhd"
        result  = "cdefghid"

         ij......j
         i.i.....j
        "abcdefghid"
        { a:1, b:1, c:1, d:1 }

        "cddh"
        { c:1, d:2, h:1 }

     */
    private fun slidingWindow(s: String, t: String): String {
        if (s.isEmpty()) return ""
        if (t.isEmpty()) return ""
        if (s.length < t.length) return ""

        var result = ""
        var resultLength = Int.MAX_VALUE
        var i = 0
        var j = t.lastIndex
        val tCount = t.countMap()
        val subCount = s.substring(i, j + 1).countMap()

        while (i < j && j <= s.lastIndex) {
            val substring = s.substring(i, j + 1)

            // println("Comparing $substring from $i to $j")
            // println("t count: $tCount")
            // println("substring count: $subCount")

            if (tCount hasNotSameEntriesOn subCount) {
                j++
                s.getOrNull(j)?.also { subCount.incrementOrAdd(it) }
            } else {
                subCount.decrementOrRemove(s[i]!!)
                i++

                if (substring.length < resultLength) {
                    result = substring
                    resultLength = result.length
                }
            }
        }

        return result
    }

    private fun MutableMap<Char, Int>.incrementOrAdd(key: Char) {
        this[key] = getOrDefault(key, 0) + 1
    }

    private fun MutableMap<Char, Int>.decrementOrRemove(key: Char) {
        if (containsKey(key) && this[key]!! > 1) {
            this[key] = this[key]!! - 1
        } else {
            remove(key)
        }
    }

    /**

        s       = "abcdefghi"
        t       = "cdh"
        result  = "cdefgh"

        for i in s.indices
            for j in i + t.size until s.size
                val substring = s.substring(i, j + 1)
                
                if (substring.containsAll(t)) return substring

     */
    private fun bruteForce(s: String, t: String): String {
        // Time: O(n² * m)
        // Space: O(1)
        // where n = s.length, m = t.length
        if (s.isEmpty()) return ""
        if (t.isEmpty()) return ""

        var result = ""
        val tCount = t.countMap() // O(m)

        for (i in s.indices) { // O(n² * m)
            for (j in i + t.lastIndex until s.length) { // O(n * m)
                val substring = s.substring(i, j + 1)
                val subCount = substring.countMap() // O(n)

                // println("Evaluaing $substring from $i to $j")
                // println("t count map: $tCount")
                // println("substring count map: $subCount")

                result = when {
                    tCount hasNotSameEntriesOn subCount -> result // O(m)
                    result.isEmpty() -> substring
                    result.length > j - i -> substring
                    else -> result
                }
            }
        }

        return result
    }

    // Time: O(n)
    // Space: O(1)
    private fun String.countMap(): MutableMap<Char, Int> = hashMapOf<Char, Int>().apply {
        for (char in this@countMap) {
            this[char] = getOrDefault(char, 0) + 1
        }
    }
    
    // Time: O(m)
    private infix fun Map<Char, Int>.hasNotSameEntriesOn(other: Map<Char, Int>): Boolean {
        return any { (k, v) -> other[k] == null || other[k]!! < v }
    }
}
