class Solution {
    fun solve(board: Array<CharArray>) {
        dfs(board)
    }

    /**

        Edge cases:
            - board is empty, return

        dfs(cell, visited)
            if cell is not on board, return false
            if (cell is not region) return true
            if (cell in visited) return true

            visited.add(cell)

            foreach direction 
                if (dfs(cell + direction, visited).not()) return false

        traverse the board
            for each cell in the middle
                if region
                    val visited = hashSet()
                    val surrounded = dfs(cell, visited)

                    if (surrounded)
                        foreach visited capture

     */
    private fun dfs(board: Array<CharArray>) {
        if (board.isEmpty()) return

        fun dfs(r: Int, c: Int, visited: HashSet<Pair<Int, Int>>): Boolean {
            if (r < 0 || r > board.lastIndex) return false
            if (c < 0 || c > board[r].lastIndex) return false
            if (board[r][c] != 'O') return true
            if ((r to c) in visited) return true

            visited.add(r to c)

            for ((i, j) in Directions) {
                val (x, y) = (r + i) to (c + j)

                if (dfs(x, y, visited).not()) return false
            }

            return true
        }

        for (r in board.indices) {
            for (c in board[r].indices) {
                val visited = HashSet<Pair<Int, Int>>()
                val surrounded = dfs(r, c, visited)

                if (surrounded) {
                    visited.forEach { (i, j) ->
                        board[i][j] = 'X'
                    }
                }
            }
        }
    }

    private companion object {
        private val Directions = listOf(
            1 to 0, // Down
            0 to -1, // Left
            -1 to 0, // Up
            0 to 1, // Right
        )
    }
}
