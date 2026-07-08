class Solution {
    /**

        Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. 
        
        If such a substring does not exist, return an empty string "".

        You may assume that the correct output is always unique.

     */
    fun minWindow(s: String, t: String): String {
        return bruteForce(s, t)
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
        if (s.isEmpty()) return ""
        if (t.isEmpty()) return ""

        var result = ""
        val tCount = t.countMap()

        for (i in s.indices) {
            for (j in i + t.lastIndex until s.length) {
                val substring = s.substring(i, j + 1)
                val subCount = substring.countMap()

                // println("Evaluaing $substring from $i to $j")
                // println("t count map: $tCount")
                // println("substring count map: $subCount")

                result = when {
                    tCount hasNotSameEntriesOn subCount -> result
                    result.isEmpty() -> substring
                    result.length > j - i -> substring
                    else -> result
                }
            }
        }

        return result
    }

    private fun String.countMap(): Map<Char, Int> = hashMapOf<Char, Int>().apply {
        for (char in this@countMap) {
            this[char] = getOrDefault(char, 0) + 1
        }
    }
    
    // {a=1, b=1, c=1, d=2} vs {a=1, b=5, c=1, d=2}
    private infix fun Map<Char, Int>.hasNotSameEntriesOn(other: Map<Char, Int>): Boolean {
        return any { (k, v) -> other[k] == null || other[k]!! < v }
    }
}
