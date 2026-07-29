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

    /**

        - for each s1 index = m, treat it as middle
        - iterate s3 indexes, t

        m = 0
        m = 1
        m = 2
              i
        s1 = "aaaa" 
              j
        s2 = "bbbb"
        
        k = i + j
              k
        s3 = "aabbbbaa"

        decisions:
            - if i > s1.lastIndex, return false
            - if j > s2.lastIndex, return false
            - if i + j == s3.length, return true

            if (s1[i] != s3[i + j] && s2[j] != s3[i + j]) return false

            return dfs(i + 1, j) || dfs(i, j + 1)

     */
    private fun intuition(s1: String, s2: String, s3: String): Boolean {
        fun dfs(i: Int = 0, j: Int = 0): Boolean {
            // println("Checking i=$i, j=$j, on s3 ${i + j}")

            if (i > s1.lastIndex && j > s2.lastIndex) return true
            if (i > s1.lastIndex) return s2[j] == s3[i + j] && dfs(i, j + 1)
            if (j > s2.lastIndex) return s1[i] == s3[i + j] && dfs(i + 1, j)

            // println("Evaluating i=$i, j=$j, on s3 ${i + j}")
            
            if (s1[i] != s3[i + j] && s2[j] != s3[i + j]) return false

            return dfs(i + 1, j) || dfs(i, j + 1)
        }

        return dfs()
    }
}
