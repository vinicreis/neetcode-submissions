class Solution {
    /**

        Edge cases:
            - if s1 is empty, return s2 == s3
            - if s2 is empty, return s1 == s3
            - if s3 is empty, return s1 is empty && s2.isEmpty()

     */
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.isEmpty()) return s2 == s3
        if (s2.isEmpty()) return s2 == s3
        if (s3.isEmpty()) return s2.isEmpty() && s1.isEmpty()

        return intuition(s1, s2, s3)
    }
    
    private fun intuition(s1: String, s2: String, s3: String): Boolean {
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
