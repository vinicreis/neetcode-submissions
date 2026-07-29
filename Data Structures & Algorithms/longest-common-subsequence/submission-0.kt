class Solution {
    /**

        Edge cases:
            if any text is empty, return 0

     */
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        if (text1.isEmpty()) return 0
        if (text2.isEmpty()) return 0

        return dfs(text1, text2)
    }

    /**
                   i
                        i
                        i    i
        text1 = [ 'c', 'a', 't' ]
                        j    j 
        text2 = [ 'c', 'r', 'a', 'b', 't' ]

        fun dfs(i: Int = 0, j: Int = 0): Int {
            if (i >= text1.length) return 0
            if (j >= text2.length) return 0

            return if (text1[i] != text2[j]) {
                dfs(i + 1, j) + dfs(i, j + 1)
            } else {
                1 + dfs(i + 1, j + 1)
            }
        }

     */
    private fun dfs(text1: String, text2: String): Int {
        fun dfs(i: Int = 0, j: Int = 0): Int {
            if (i >= text1.length) return 0
            if (j >= text2.length) return 0

            // println("Evaluating i $i=${text1[i]} and j $j=${text2[j]}")
            return if (text1[i] != text2[j]) {
                dfs(i + 1, j)
            } else {
                1 + dfs(i + 1, j + 1)
            }
        }

        return dfs()
    }
}
