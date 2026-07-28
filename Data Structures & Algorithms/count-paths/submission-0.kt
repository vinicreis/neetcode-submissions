class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0
        if (m == 1 || n == 1) return 1

        return dfsV1(m, n)
    }

    /**

        Edge cases:
            - m == 0, return 0
            - n == 0, return 0

        Base cases:
            - m == 1, return 1
            - n == 1, return 1

        Can use a recursive DFS, return int
            each time we leave a path, increment

        dfs(i: Int, j: Int): Int
            if (i < 0 || i >= m) return 0 // invalid path
            if (j < 0 || j >= n) return 0 // invalid path
            if (i == m - 1 && j == n - 1) return 1 // reached end

            return dfs(i + 1, j) + dfs(i, j + 1)
     */
    private fun dfsV1(m: Int, n: Int): Int {
        fun dfs(i: Int = 0, j: Int = 0): Int {
            if (i < 0 || i >= m) return 0 // invalid path
            if (j < 0 || j >= n) return 0 // invalid path
            if (i == m - 1 && j == n - 1) return 1 // reached end

            return dfs(i + 1, j) + dfs(i, j + 1)
        }

        return dfs()
    }
}
