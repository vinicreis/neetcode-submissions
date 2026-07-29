class Solution {
    /**

        - Edge cases
            - word1 == word2, return 0

     */
    fun minDistance(word1: String, word2: String): Int {
        if (word1 == word2) return 0
        
        return intuition(word1, word2)
    }

    /**

        3 actions
            1. insert
            2. delete
            3. replace

        Where to do each one?
            - iterate through word1, i
                - if word1[i] == word2[i], move to next
                - otherwise, try all operations, return minimum

        Insert or replace with what?
            - with the current char on word2?

        When to do each one?
            - insert:  
                1. when word1.len < word2.len
            - delete:  
                1. when word1.len > word2.len
            - replace: 
                1. when word1.len = word2.len

        check with dfs?

     */
    private fun intuition(word1: String, word2: String): Int {
        val m = word1.length
        val n = word2.length

        fun dfs(i: Int = 0, j: Int = 0): Int {
            if (i == m) return n - j
            if (j == n) return m - i
            if (word1[i] == word2[j]) return dfs(i + 1, j + 1)

            val result = 1 + minOf(
                dfs(i + 1, j),      // delete
                dfs(i, j + 1),      // add
                dfs(i + 1, j + 1),  // replace
            )

            return result
        }

        return dfs()
    }
}
