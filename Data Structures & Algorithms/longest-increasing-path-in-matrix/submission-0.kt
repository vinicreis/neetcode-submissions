class Solution {
    /**

        Edge cases:
            - matrix is empty, return zero
            - matriz 1x1, return 1

     */
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        if (matrix.size == 0) return 0
        if (matrix.size == 1 && matrix.first().size == 1) return 1

        return intuition(matrix)
    }

    /**

        Traverse the graph, starting from all cells
        pass a previous value to check if its greater
        if not greater, stop

     */
    private fun intuition(matrix: Array<IntArray>): Int {
        fun dfs(
            coord: Coord = 0 to 0,
            prev: Int = -1,
            visited: Array<BooleanArray> = Array(matrix.size) { BooleanArray(matrix[it].size) },
        ): Int {
            val (i, j) = coord
            if (i < 0 || i > matrix.lastIndex) return 0
            if (j < 0 || j > matrix[i].lastIndex) return 0
            if (visited[i][j]) return 0
            
            visited[i][j] = true

            if (matrix[i][j] <= prev) return 0

            var result = 0

            for (direction in Direction.entries) {
                result = max(result, 1 + dfs(coord + direction, matrix[i][j], visited))
            }

            return result
        }

        var result = 0

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                result = max(result, dfs(i to j))
            }
        }

        return result
    }

    private companion object {
        private enum class Direction(val x: Int = 0, val y: Int = 0) {
            Up(x = -1),
            Right(y = 1),
            Down(x = 1),
            Left(y = -1),
        }

        private operator fun Coord.plus(direction: Direction): Coord {
            return (first + direction.x) to (second + direction.y)
        }
    }
}

private typealias Coord = Pair<Int, Int>
