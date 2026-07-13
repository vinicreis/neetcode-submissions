class Solution {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        return solution(grid)
    }

    /**

        - DFS
        - every time we find an island, we enter the search
        - increment a temp result for every land
        - every time we leave the search, we have a total area
        - compute the max on the result
        - return the result

        - Edge cases:
            - empty grid (no rows), return 0 max area
            - empty grid items (no columns), algorithm will handle
            - invalid values for cells (is no 0 nor 1), leave the algorithm if land could be more than 1

     */
    private fun solution(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0

        var result = 0

        fun dfs(i: Int = 0, j: Int = 0): Int {
            if (i < 0 || i > grid.lastIndex) return 0
            if (j < 0 || j > grid[i].lastIndex) return 0
            if (grid[i][j] < 1) return 0

            val temp = grid[i][j]
            grid[i][j] = 0

            return temp + dfs(i, j - 1) + dfs(i - 1, j) + dfs(i, j + 1) + dfs(i + 1, j)
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] <= 0) continue

                result = max(result, dfs(i, j))
            }
        }

        return result
    }
}
