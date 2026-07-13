private typealias Direction = IntArray

class Solution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        return solution(grid)
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
    private fun solution(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return -1

        var minutes = 0

        fun rotAround(i: Int, j: Int, visited: Array<BooleanArray>) {
            if (grid[i][j] != Rotten) return
            if (visited[i][j]) return

            for (direction in Directions.All) {
                val x = i + direction[0]
                val y = j + direction[1]
                
                if (x < 0 || x > grid.lastIndex) continue
                if (y < 0 || y > grid[x].lastIndex) continue
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
                    if (grid[i][j] == Rotten) rotAround(i, j, visited)
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
            val Current: Direction = intArrayOf(0, 0)
            val Up: Direction = intArrayOf(-1, 0)
            val Down: Direction = intArrayOf(1, 0)
            val Left: Direction = intArrayOf(0, -1)
            val Right: Direction = intArrayOf(0, 1)

            val All: List<Direction> = listOf(Up, Down, Left, Right)
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
                            val x = i + direction[0]
                            val y = j + direction[1]
                            
                            if (x < 0 || x > lastIndex) continue
                            if (y < 0 || y > this[x].lastIndex) continue

                            if (this[x][y] == Rotten) return true
                        }
                    }
                }
            }

            return false
        }

        private fun Array<IntArray>.isValidOn(i: Int, j: Int, direction: Direction = Directions.Current): Boolean {
            val x: Int = i + direction[0]
            val y: Int = i + direction[1]

            if (x < 0 || x > lastIndex) return false
            if (y < 0 || y > this[x].lastIndex) return false

            println("($x, $y) is valid")

            return true
        }
    }
}
