class Solution {
    fun solveNQueens(n: Int): List<List<String>> {
        return solution(n)
    }

    /**

        Edge cases: 
            - n < 1, empty board
            - n == 1, 1 queen only

        - Backtracking
        
        fun dfs(i: Int, j: Int)


     */
    private fun solution(n: Int): List<List<String>> {
        if (n < 1) return emptyList()
        if (n == 1) return listOf(listOf("Q"))

        val result = mutableListOf<List<String>>()
        val board = Array(n) { CharArray(n) { Empty } }

        println(board.queensAttackEachOther())

        arrayOf(
            charArrayOf('.', 'Q', '.', '.'),
            charArrayOf('.', '.', '.', 'Q'),
            charArrayOf('Q', '.', '.', '.'),
            charArrayOf('.', '.', 'Q', '.'),
        ).also { println(it.queensAttackEachOther()) }

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
        i = 0, j = 3
        i = 2, j = 3
        i = 2, j = 1
        i = 3, j = 0

     */
    private fun Array<CharArray>.queensAttackEachOther(): Boolean {
        for (i in indices) {
            for (j in this[i].indices) {
                if (this[i][j] != Queen) continue

                // Check row
                if (Queen in this[i]) return true

                // Check column
                for (k in indices) if (this[k][j] == Queen) return true

                // Check diagonal right-up
                for (k in 1 until size) {
                    val (x, y) = (i - k) to (j + k)

                    println("Checking right up diag on ($x, $y)")
                    
                    if (hasNoCoord(x, y)) continue
                    if (this[x][y] == Queen) return true
                }

                // Check diagonal left-up
                for (k in 1 until size) {
                    val (x, y) = (i - k) to (j - k)

                    println("Checking left up diag on ($x, $y)")
                    
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
