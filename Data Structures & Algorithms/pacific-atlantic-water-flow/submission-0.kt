typealias Coords = Pair<Int, Int>

class Solution {
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        return solution(heights)
    }

    private fun solution(heights: Array<IntArray>): List<List<Int>> {
        if (heights.isEmpty()) return emptyList()

        val result = mutableListOf<List<Int>>()
        val reachesAtlantic = HashSet<Coords>()
        val reachesPacific = HashSet<Coords>()

        fun dfs(to: Coords, visited: HashSet<Coords>, prevHeight: Int = heights[to]) {
            if (heights.has(to).not()) return
            if (to in visited) return
            if (prevHeight > heights[to]) return

            visited.add(to)

            for (direction in Direction.entries) {
                dfs(to + direction, visited, heights[to])
            }
        }
    
        for (i in heights.indices) {
            dfs(i to 0, reachesAtlantic)
            dfs(i to heights[i].lastIndex, reachesPacific)
        }
    
        for (j in heights[0].indices) {
            dfs(0 to j, reachesAtlantic)
            dfs(heights.lastIndex to j, reachesPacific)
        }

        for (i in heights.indices) {
            for (j in heights[i].indices) {
                val coord = i to j

                if (coord in reachesAtlantic && coord in reachesPacific) {
                    result.add(listOf(i, j))
                }
            }
        }

        return result
    }

    private companion object {
        private enum class Direction(val r: Int, val c: Int) {
            Up(-1, 0),
            Down(1, 0),
            Left(0, -1),
            Right(0, 1),
            ;
        }

        private operator fun Array<IntArray>.get(coord: Coords) = this[coord.first][coord.second]
        private fun Array<IntArray>.has(coord: Coords): Boolean {
            val (x, y) = coord

            if (x < 0 || x > lastIndex) return false
            if (y < 0 || y > this[x].lastIndex) return false

            return true
        }

        private fun Array<IntArray>.isPacificAt(x: Int, y: Int): Boolean = x < 0 || y < 0
        private fun Array<IntArray>.isAtlanticAt(x: Int, y: Int): Boolean = x > lastIndex || (x >= 0 && y > this[x].lastIndex)

        private operator fun Coords.plus(direction: Direction): Coords {
            return (first + direction.r) to (second + direction.c)
        }
    }
}
