class Solution {
    /**

        You are given two strings s1 and s2.

        Return true if s2 contains a permutation of s1, or false otherwise. 
        
        That means if a permutation of s1 exists as a substring of s2, then return true.

        Both strings only contain lowercase letters.

     */
    fun checkInclusion(s1: String, s2: String): Boolean {
        return solution2(s1, s2)
    }

    /**

        - We saw that sorting helps
        - A frequency map?
            - 

                        
            lecabee -> abceeel  -> { a: 1, b: 1, c: 1, e: 3, l: 1 }
            abc     -> abc      -> { a: 1, b: 1, c: 1 }

     */
    private fun solution2(s1: String, s2: String): Boolean {
        // Time: O(n + m)
        // Space: O(n + m)
        if (s1.isEmpty()) return true
        if (s2.isEmpty()) return false
        if (s2.length < s1.length) return false

        val s1Frequencies = hashMapOf<Char, Int>()

        for (c in s1) {
            s1Frequencies[c] = s1Frequencies.getOrDefault(c, 0) + 1
        }

        for (i in s2.indices) {
            val s2Frequencies = hashMapOf<Char, Int>()
            var found = 0

            for (j in i until s2.length) {
                s2Frequencies[s2[j]] = s2Frequencies.getOrDefault(s2[j], 0) + 1

                if (s1Frequencies.getOrDefault(s2[j], 0) < s2Frequencies[s2[j]]!!) break
                if (s1Frequencies.getOrDefault(s2[j], 0) == s2Frequencies[s2[j]]!!) found++
                if (found == s1Frequencies.size) return true
            }
        }

        return false
    }

    /**

        s1 = "abc"
        s2 = "lecabee"

        brute force:
            for i in s1
                var found = ""

                for j in s2
                    if (s1[i] == s2[j])
                        found += s2[j].toString()
                    else 
                        break

                if (found == s2) return true

            return false
                    
     */
    private fun bruteForce(s1: String, s2: String): Boolean {
        // Time: O(n * m)
        // Space: O(n + m)
        // where n = s1.length, m = s2.length
        if (s1.isEmpty()) return true
        if (s2.isEmpty()) return false
        if (s2.length < s1.length) return false

        val sortedS1 = s1.toCharArray().sorted().joinToString(separator = "")

        for (i in s2.indices) {
            for (j in i until s2.length) {
                val substring = s2.substring(i, j + 1).toCharArray().sorted().joinToString(separator = "")

                if (substring == sortedS1) return true
            }
        }

        return false
    }
}
