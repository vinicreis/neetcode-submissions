typealias Coordinates = Pair<Int, Int>

class Solution {
    fun islandsAndTreasure(grid: Array<IntArray>) {
        return dfs(grid)
    }

    /**
        traverse the grid: DFS

        dfs: i, j, distance
            if invalid coordinates return INF
            if water return INF

            if treasure
                return distance
            otherwise
                return min of all directions

        for each cell
            if land:
                grid[i][j] = dfs(i, j, distance = 1)
     */

    private fun dfs(grid: Array<IntArray>) {
        if (grid.isEmpty()) return

        fun dfs(coord: Pair<Int, Int>, visited: Array<BooleanArray>, distance: Int = 0): Int {
            if (grid hasNot coord) return Land
            if (grid.at(coord) == Water) return Land
            if (grid.at(coord) == Chest) return distance
            if (visited.at(coord)) return Land

            visited wasVisitedAt coord

            return minOf(
                dfs(coord + Direction.Left, visited, distance + 1),
                dfs(coord + Direction.Up, visited, distance + 1),
                dfs(coord + Direction.Right, visited, distance + 1),
                dfs(coord + Direction.Down, visited, distance + 1),
            )
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                val coord = i to j

                if (grid.at(coord) == Land) {
                    val visited = Array(grid.size) { BooleanArray(grid[i].size) }

                    grid.setAt(coord, value = dfs(coord, visited))
                }
            }
        }

        return
    }

    private companion object {
        private const val Water = -1
        private const val Chest = 0
        private const val Land = Int.MAX_VALUE

        private infix fun Array<IntArray>.has(coord: Coordinates): Boolean {
            if (coord.first < 0 || coord.first > lastIndex) return false
            if (coord.second < 0 || coord.second > this[coord.first].lastIndex) return false

            return true
        }

        private infix fun Array<IntArray>.hasNot(coord: Coordinates): Boolean = (this has coord).not()

        private fun Array<IntArray>.at(coord: Coordinates): Int = this[coord.first][coord.second]
        private fun Array<IntArray>.setAt(coord: Coordinates, value: Int) {
            this[coord.first][coord.second] = value
        }

        private fun Array<BooleanArray>.at(coord: Coordinates): Boolean = this[coord.first][coord.second]
        private infix fun Array<BooleanArray>.wasVisitedAt(coord: Coordinates) {
            this[coord.first][coord.second] = true
        }

        private enum class Direction(val r: Int, val c: Int) {
            Right(r = 0, c = 1),
            Down(r = 1, c = 0),
            Left(r = 0, c = -1),
            Up(r = -1, c = 0),
            ;
        }

        private operator fun Coordinates.plus(direction: Direction): Coordinates {
            return (first + direction.r) to (second + direction.c)
        }
    }
}
