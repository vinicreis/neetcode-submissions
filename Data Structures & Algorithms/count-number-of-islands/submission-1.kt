class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        return dfs(grid)
    }

    private fun dfs(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0

        var result = 0

        fun dfs(i: Int = 0, j: Int = 0) {
            if (i < 0 || i > grid.lastIndex) return
            if (j < 0 || j > grid[i].lastIndex) return
            if (grid[i][j] == '0') return

            grid[i][j] = '0'

            dfs(i, j - 1)
            dfs(i - 1, j)
            dfs(i, j + 1)
            dfs(i + 1, j)
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '1') {
                    dfs(i, j)
                    result++
                }
            }
        }

        return result
    }
}
