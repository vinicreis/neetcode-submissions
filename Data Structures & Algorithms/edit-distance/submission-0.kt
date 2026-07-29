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
        fun dfs(i: Int = 0, j: Int = 0, cur: String = word1): Int {
            if (cur == word2) return 0
            if (i >= cur.length && j >= word2.length) return 0
            if (i < cur.length && j < word2.length && cur[i] == word2[j]) {
                return dfs(i + 1, j + 1, cur)
            }
            
            return when {
                cur.length < word2.length -> 1 + dfs(i, j, cur.add(i, word2[j]))
                cur.length > word2.length -> 1 + dfs(i, j, cur.removeAt(i))
                else -> 1 + dfs(i, j, cur.replaceAt(i, word2[j]))
            }
        }

        return dfs()
    }

    private fun String.add(i: Int, char: Char): String {
        return substring(0, i) + char + substring(i, length)
    }

    private fun String.replaceAt(i: Int, char: Char): String {
        return substring(0, i) + char + substring(i + 1, length)
    }

    private fun String.removeAt(i: Int): String {
        if (i == lastIndex) return substring(0, i)

        return substring(0, i) + substring(i + 1, length)
    }
}
