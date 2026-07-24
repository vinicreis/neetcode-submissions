class Solution {
    fun solveNQueens(n: Int): List<List<String>> {
        return solution(n)
    }

    /**

        Edge cases: 
            - n < 1, empty board
            - n == 1, 1 queen only

        - Backtracking
        
        fun dfs(i: Int = 0, j: Int = 0, queens: Int = 0, board: Array<CharArray>)
            if queens = n
                if (!board.queensAttackEachOther) 
                    result.add(board.toResult())
                return

            if board.hasNoCoord(i, j) return

            board[i][j] = Queen

            val (l, m) = if i == board.lastIndex i + 1 to 0 else i to j + 1
            
            dfs(l, m, queens + 1, board)
            board[i][j] = Empty
            dfs(l, m, queens, board)

     */
    private fun solution(n: Int): List<List<String>> {
        if (n < 1) return emptyList()
        if (n == 1) return listOf(listOf("Q"))

        val result = mutableListOf<List<String>>()

        fun dfs(
            i: Int = 0,
            j: Int = 0,
            queens: Int = 0,
            board: Array<CharArray> = Array(n) { CharArray(n) { Empty } },
        ) {
            if (queens == n) {
                if (board.queensAttackEachOther().not()) {
                    result.add(board.toResult())
                }

                return
            }

            if (board.hasNoCoord(i, j)) return

            board[i][j] = Queen
            
            val (k, l) = if (j == n - 1) i.inc() to 0 else i to j.inc()

            dfs(k, l, queens + 1, board)

            board[i][j] = Empty

            dfs(k, l, queens, board)
        }

        dfs()

        return result
    }

    private fun Array<CharArray>.toResult(): List<String> {
        return map { it.joinToString(separator = "") }
    }

    /**

        ['.', 'Q', '.', '.'],
        ['.', '.', 'Q', 'Q'],
        ['Q', '.', '.', '.'],
        ['.', '.', 'Q', '.'],
        
        for i = 1, j = 2
        diagonals must be

        i = 0, j = 1
        RU
        i = 0, j = 3
        RD
        i = 2, j = 3
        LD
        i = 2, j = 1
        i = 3, j = 0

     */
    private fun Array<CharArray>.queensAttackEachOther(): Boolean {
        for (i in indices) {
            for (j in this[i].indices) {
                if (this[i][j] != Queen) continue

                // Check row
                for (k in indices) {
                    if (k == j) continue
                    if (this[i][k] == Queen) return true
                }

                // Check column
                for (k in indices) {
                    if (k == i) continue
                    if (this[k][j] == Queen) return true
                }

                // Check diagonal right-up
                for (k in 1 until size) {
                    val (x, y) = (i - k) to (j + k)

                    if (hasNoCoord(x, y)) continue
                    if (this[x][y] == Queen) return true
                }

                // Check diagonal left-up
                for (k in 1 until size) {
                    val (x, y) = (i - k) to (j - k)

                    if (hasNoCoord(x, y)) continue
                    if (this[x][y] == Queen) return true
                }

                // Check diagonal right-down
                for (k in 1 until size) {
                    val (x, y) = (i + k) to (j + k)
                    
                    if (hasNoCoord(x, y)) continue
                    if (this[x][y] == Queen) return true
                }

                // Check diagonal left-down
                for (k in 1 until size) {
                    val (x, y) = (i - k) to (j - k)

                    if (hasNoCoord(x, y)) continue
                    if (this[x][y] == Queen) return true
                }
            }
        }

        return false
    }

    private fun Array<CharArray>.hasNoCoord(x: Int, y: Int): Boolean {
        if (x < 0 || x > lastIndex) return true
        if (y < 0 || y > this[x].lastIndex) return true

        return false
    }

    private companion object {
        private const val Empty = '.'
        private const val Queen = 'Q'
    }
}
