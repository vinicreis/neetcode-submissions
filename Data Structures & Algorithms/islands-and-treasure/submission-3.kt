typealias Coordinates = Pair<Int, Int>

class Solution {
    fun islandsAndTreasure(grid: Array<IntArray>) {
        return bfs(grid)
    }

    private fun bfs(grid: Array<IntArray>) {
        if (grid.isEmpty()) return

        fun bfs(coord: Coordinates): Int {
            val visited = Array(grid.size) { BooleanArray(grid[it].size) }
            val queue = ArrayDeque<Coordinates>()

            queue.add(coord)
            visited wasVisitedAt coord
            var distance = 0

            while (queue.isNotEmpty()) {
                repeat(queue.size) {
                    val coord = queue.removeFirst()

                    if (grid.at(coord) == Chest) return distance

                    directions@ for (nextCoord in coord.directions()) {
                        if (grid hasNot nextCoord) continue@directions
                        if (visited.at(nextCoord)) continue@directions
                        if (grid.at(nextCoord) == Water) continue@directions

                        visited wasVisitedAt nextCoord
                        queue.add(nextCoord)
                    }
                }

                distance++
            }

            return Land
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                val coord = i to j

                if (grid.at(coord) == Land) {
                    grid.setAt(coord, value = bfs(coord))
                }
            }
        }
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

        val visited = Array(grid.size) { BooleanArray(grid[it].size) }

        fun dfs(coord: Pair<Int, Int>, distance: Int = 0): Int {
            if (grid hasNot coord) return Land
            if (grid.at(coord) == Water) return Land
            if (grid.at(coord) == Chest) return distance
            if (visited.at(coord)) return Land

            visited wasVisitedAt coord

            val result = minOf(
                dfs(coord + Direction.Left, distance + 1),
                dfs(coord + Direction.Up, distance + 1),
                dfs(coord + Direction.Right, distance + 1),
                dfs(coord + Direction.Down, distance + 1),
            )

            visited wasNotVisitedAt coord

            return result
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                val coord = i to j

                if (grid.at(coord) == Land) {
                    grid.setAt(coord, value = dfs(coord))
                }
            }
        }
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

        private infix fun Array<BooleanArray>.wasNotVisitedAt(coord: Coordinates) {
            this[coord.first][coord.second] = false
        }

        private enum class Direction(val r: Int, val c: Int) {
            Up(r = -1, c = 0),
            Down(r = 1, c = 0),
            Left(r = 0, c = -1),
            Right(r = 0, c = 1),
            ;
        }

        private operator fun Coordinates.plus(direction: Direction): Coordinates {
            return (first + direction.r) to (second + direction.c)
        }

        private fun Coordinates.directions(): List<Coordinates> {
            return Direction.entries.map { direction -> this + direction }
        }
    }
}
