class Solution {
    /**

        You are given a string s consisting of only uppercase english characters and an integer k.
        
        You can choose up to k characters of the string and replace them with any other uppercase English character.

        After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

     */
    fun characterReplacement(s: String, k: Int): Int {
        return slidingWindow(s, k)
    }

    /**

         i    j
        "AAABABB"
        k      = 1, 2, 3
        result = 5, 6, 7
     */
    private fun slidingWindow(s: String, k: Int): Int {
        // Time: O(n²)
        // Space: O(n), worst case
        var result = 0
        var i = 0
        var j = 0
        var maxFrequency = 0
        val found = hashMapOf<Char, Int>()

        while (j < s.length) {
            found[s[j]] = found.getOrDefault(s[j], 0) + 1
            maxFrequency = max(maxFrequency, found[s[j]]!!)
            val size = j - i + 1

            if (size - maxFrequency <= k) {
                result = max(result, size)
            } else {
                i = j
                found.clear()
                maxFrequency = 0
            }

            j++
        }

        return result
    }

    /**

        s      = "SASPOKS"
        k      = 2, 1, 0
        result = 7, 4, 3

        Edge cases:
            - s is empty    -> 0
            - s.length == 1 -> 1

        Brute force:
            - "SASPOKS"
            - k = 2
     */
    private fun bruteForce(s: String, k: Int): Int {
        // Time: O(n²)
        // Space: O(n), worst case
        var result = 0

        for (i in s.indices) {
            var found = hashMapOf<Char, Int>()
            var maxFrequency = 0

            for (j in i until s.length) {
                val size = j - i + 1

                found[s[j]] = found.getOrDefault(s[j], 0) + 1
                maxFrequency = max(maxFrequency, found[s[j]]!!)

                if (size - maxFrequency <= k) {
                    result = max(result, size)
                }
            }
        }

        return result
    }
}
