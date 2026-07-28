class Solution {
    fun countSubstrings(s: String): Int {
        return dp(s)
    }

    private fun dp(s: String): Int {
        // Time:  O(n³)
        // Space: O(1)
        if (s.length <= 1) return s.length

        var result = 0
        val n = s.length
        val memo = Array(n) { BooleanArray(n) }

        for (i in s.lastIndex downTo 0) {
            for (j in i until n) {
                if (s[i] == s[j] && (j - i <= 2 || memo[i+1][j-1])) {
                    memo[i][j] = true

                    result++
                }
            }
        }

        return result
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
