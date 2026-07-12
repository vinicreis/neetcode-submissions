class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        return dfs(grid)
    }

    private fun dfs(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0

        var result = 0
        val visited = Array(grid.size) { BooleanArray(grid[it].size) }

        fun dfs(i: Int = 0, j: Int = 0) {
            if (i < 0 || i > grid.lastIndex) return
            if (j < 0 || j > grid[i].lastIndex) return
            if (visited[i][j]) return

            visited[i][j] = true

            if (grid[i][j] == '1') {
                dfs(i, j - 1)
                dfs(i - 1, j)
                dfs(i, j + 1)
                dfs(i + 1, j)

                result++
            }
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                dfs(i, j)
            }
        }

        return result
    }
}
