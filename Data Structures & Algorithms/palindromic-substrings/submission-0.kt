class Solution {
    fun countSubstrings(s: String): Int {
        return doublePointerV1(s)
    }
    
    /**

        Edge cases:
            - s.len <= 1, return s.length

        l,i    r        
        "aaaaaaa"

     */
    private fun doublePointerV1(s: String): Int {
        // Time:  O(n³)
        // Space: O(1)
        if (s.length <= 1) return s.length

        var result = 0

        for (i in s.indices) { // O(n³)
            for (j in i until s.length) { // O(n²)
                var l = i
                var r = j

                while (l < r && s[l] == s[r]) { // O(n / 2) = O(n)
                    l++; r--
                }

                if (l >= r) result++
            }
        }

        return result
    }
}
