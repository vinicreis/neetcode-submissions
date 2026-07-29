class Solution {
    /**

        Edge cases:
            - if s1 is empty, return s2 == s3
            - if s2 is empty, return s1 == s3
            - if s3 is empty, return s1 is empty && s2.isEmpty()

     */
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        return dp(s1, s2, s3)
    }
    
    private fun dp(s1: String, s2: String, s3: String): Boolean {
        // Time:  O(2^(m + n))
        // Space: O(m + n)

        val memo = Array(s1.length + 1) { IntArray(s2.length + 1) { -1 } }

        fun dfs(i: Int = 0, j: Int = 0): Boolean {
            if (i + j == s3.length) return i == s1.length && j == s2.length

            var result = false

            if (i <= s1.lastIndex && s1[i] == s3[i + j]) {
                result = dfs(i + 1, j)
            }

            if (result.not() && j <= s2.lastIndex && s2[j] == s3[i + j]) {
                result = dfs(i, j + 1)
            }

            memo[i][j] = if (result) 1 else 0

            return result
        }

        return dfs()
    }
    
    private fun intuition(s1: String, s2: String, s3: String): Boolean {
        // Time:  O(2^(m + n))
        // Space: O(m + n)
        fun dfs(i: Int = 0, j: Int = 0): Boolean {
            if (i + j == s3.length) return i == s1.length && j == s2.length

            if (i <= s1.lastIndex && s1[i] == s3[i + j]) {
                if (dfs(i + 1, j)) return true
            }

            if (j <= s2.lastIndex && s2[j] == s3[i + j]) {
                if (dfs(i, j + 1)) return true
            }

            return false
        }

        return dfs()
    }
}
