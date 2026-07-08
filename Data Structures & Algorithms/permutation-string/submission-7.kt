class Solution {
    /**

        You are given two strings s1 and s2.

        Return true if s2 contains a permutation of s1, or false otherwise. 
        
        That means if a permutation of s1 exists as a substring of s2, then return true.

        Both strings only contain lowercase letters.

     */
    fun checkInclusion(s1: String, s2: String): Boolean {
        return intuition(s1, s2)
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
    private fun intuition(s1: String, s2: String): Boolean {
        if (s1.isEmpty()) return true
        if (s2.isEmpty()) return false
        if (s2.length < s1.length) return false

        val sortedS1 = s1.toCharArray().sorted().concatToString()

        for (i in s2.indices) {
            for (j in i until s2.length) {
                val substring = s2.substring(i, j + 1).toCharArray().sorted().concatToString()

                if (substring == sortedS1) return true
            }
        }

        return false
    }
}
