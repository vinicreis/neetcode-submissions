class Solution {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        return backtracking(board, word)
    }

    private fun backtracking(board: Array<CharArray>, word: String): Boolean {
        if (word.isEmpty()) return true
        if (board.isEmpty()) return false
        
        val visited: Array<BooleanArray> = Array(board.size) { BooleanArray(board[it].size) }

        fun backtracking(
            i: Int = 0,
            j: Int = 0,
            n: Int = 0,
        ): Boolean {
            if (n == word.length) return true
            if (i < 0 || i > board.lastIndex) return false
            if (j < 0 || j > board[i].lastIndex) return false
            if (visited[i][j]) return false
            if (board[i][j] != word[n]) return false

            visited[i][j] = true

            val result = backtracking(i, j - 1, n + 1)
                || backtracking(i - 1, j, n + 1)
                || backtracking(i, j + 1, n + 1)
                || backtracking(i + 1, j, n + 1)

            visited[i][j] = false

            return result
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (backtracking(i, j)) return true
            }
        }

        return false
    }
}
