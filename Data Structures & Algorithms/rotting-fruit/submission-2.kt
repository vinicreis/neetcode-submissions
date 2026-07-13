private typealias Direction = Pair<Int, Int>
private typealias Coordinates = Pair<Int, Int>

class Solution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        return bfs(grid)
    }
    
    private fun bfs(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return -1

        var minutes = 0
        var fresh = 0
        val queue = ArrayDeque<Coordinates>()

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i to j] == Fresh) fresh++
                if (grid[i to j] == Rotten) queue.addLast(i to j)
            }
        }

        while (fresh > 0 && queue.isNotEmpty()) {
            val size = queue.size

            repeat(size) {
                val coord = queue.removeFirst()

                for (direction in Directions.All) {
                    val next = coord + direction

                    if (grid.isValidOn(next).not()) continue
                    if (grid[next] != Fresh) continue

                    grid[next] = Rotten
                    queue.addLast(next)
                    fresh--
                }
            }

            minutes++
        }

        return if (fresh == 0) minutes else -1
    }

    /**
        - empty grid, return -1
        - if no rotten, return -1
        - compute minutes = 0

        - while healthy fruits with rotten neigbors
            traverse the grid
                if rotten
                    for each neghbor
                        if is a fruit: rot it

        if all fruits are rotten, return minutes
        otherwise return -1
     */
    private fun intuition(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return -1

        var minutes = 0

        fun rotAround(coord: Coordinates, visited: Array<BooleanArray>) {
            val (i, j) = coord

            if (grid[i][j] != Rotten) return
            if (visited[i][j]) return

            for (direction in Directions.All) {
                val next = coord + direction
                val (x, y) = next
                
                if (grid.isValidOn(next).not()) continue
                if (grid[x][y] != Fresh) continue
                if (visited[x][y]) continue

                grid[x][y] = Rotten
                visited[x][y] = true
            }
        }

        while (grid.anyFresh()) {
            val visited = Array(grid.size) { BooleanArray(grid[it].size) }

            for (i in grid.indices) {
                for (j in grid[i].indices) {
                    if (grid[i][j] == Rotten) rotAround(i to j, visited)
                }
            }

            minutes++
        }

        return if (grid.allRotten()) minutes else -1
    }

    private companion object {
        private const val Empty = 0
        private const val Fresh = 1
        private const val Rotten = 2

        private object Directions {
            val Current: Direction = 0 to 0
            val Up: Direction = -1 to 0
            val Down: Direction = 1 to 0
            val Left: Direction = 0 to -1
            val Right: Direction = 0 to 1

            val All: List<Direction> = listOf(Up, Down, Left, Right)
        }

        private operator fun Array<IntArray>.get(coord: Coordinates): Int = this[coord.first][coord.second]
        private operator fun Array<IntArray>.set(coord: Coordinates, value: Int) {
            this[coord.first][coord.second] = value
        }

        private operator fun Array<BooleanArray>.get(coord: Coordinates): Boolean = this[coord.first][coord.second]
        private operator fun Array<BooleanArray>.set(coord: Coordinates, value: Boolean) {
            this[coord.first][coord.second] = value
        }

        private fun Array<IntArray>.allRotten(): Boolean {
            for (i in indices) {
                for (j in this[i].indices) {
                    if (this[i][j] == Fresh) return false
                }
            }

            return true
        }

        private fun Array<IntArray>.anyFresh(): Boolean {
            for (i in indices) {
                for (j in this[i].indices) {
                    if (this[i][j] == Fresh) {
                        for (direction in Directions.All) {
                            val (x, y) = (i to j) + direction
                            
                            if (x < 0 || x > lastIndex) continue
                            if (y < 0 || y > this[x].lastIndex) continue

                            if (this[x][y] == Rotten) return true
                        }
                    }
                }
            }

            return false
        }

        private operator fun Coordinates.plus(direction: Direction): Coordinates {
            return (first + direction.first) to (second + direction.second)
        }

        private fun Array<IntArray>.isValidOn(coord: Coordinates, direction: Direction = Directions.Current): Boolean {
            val (x, y) = coord + direction

            if (x < 0 || x > lastIndex) return false
            if (y < 0 || y > this[x].lastIndex) return false

            return true
        }
    }
}
