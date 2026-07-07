class Solution {
    /**

        Given a string s, find the length of the longest substring without duplicate characters.

        A substring is a contiguous sequence of characters within a string.

     */
    fun lengthOfLongestSubstring(s: String): Int {
        return twoPointers(s)
    }

    /**
         i  j      
        "abcabcbb"

         ij      
        "asdfghjk"
         ij      
        "aaaaaaaa"
         ij   
        "pwwkew"

        max = 3
     */
    private fun twoPointers(s: String): Int {
        // Time: O(n)
        // Space: O(1)

        if (s.isEmpty()) return 0
        if (s.length == 1) return 1

        var i = 0
        var j = 1
        var maxSequence = 0
        val found = hashMapOf<Char, Int>(s[i] to 1)

        while (j < s.length) {
            if (found.containsKey(s[j]).not()) {
                found[s[j]] = 1
            } else {
                found.clear()
                maxSequence = max(maxSequence, j - i)
                i = j
                found[s[i]] = 1
            }

            j++
        }

        maxSequence = max(maxSequence, j - i)

        return maxSequence
    }

    /**
        "hi! Itdoesnrpy"

        - Edge cases:
            - s is empty: 0
            - case sensitive? yes
            
        - intuition (brute force)
            - for every character, traverse the string looking for repeatition
     */
    private fun bruteForce(s: String): Int {
        // Time: O(n²)
        // Space: O(1)
        if (s.isEmpty()) return 0

        var maxSequence = 0

        for (start in s.indices) {
            for (end in start + 1 until s.length) {            
                if (s[start] == s[end]) {
                    maxSequence = max(maxSequence, end - start)

                    break
                }
            }
        }

        return maxSequence
    }
}
