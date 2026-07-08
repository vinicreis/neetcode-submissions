class Solution {
    /**

        You are given a string s consisting of only uppercase english characters and an integer k.
        
        You can choose up to k characters of the string and replace them with any other uppercase English character.

        After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

     */
    fun characterReplacement(s: String, k: Int): Int {
        return bruteForce(s, k)
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

            result = 0
            found = hashMapOf<Char, Int>()

            for i in s
                for j in i + 1 until s.length
                    if (s[i] == s[j] && k > 0) 
                        k--
                    else if (s[i] != s[j])
                        result = max(result, j - i)
                    else
                        break

            return result
     */
    private fun bruteForce(s: String, k: Int): Int {
        var result = 0

        for (i in s.indices) {
            var found = hashMapOf<Char, Int>()
            var maxFrequency = 0

            for (j in i until s.length) {
                found[s[j]] = found.getOrDefault(s[j], 0) + 1
                maxFrequency = max(maxFrequency, found[s[j]]!!)

                if ((j - i + 1) - maxFrequency <= k) {
                    result = max(result, (j - i + 1))
                }
            }
        }

        return result
    }
}
