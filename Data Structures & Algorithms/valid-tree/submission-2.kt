class Solution {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        return solution(n, edges)
    }

    /**



     */
    private fun solution(n: Int, edges: Array<IntArray>): Boolean {
        if (edges.size > n - 1) return false

        val adjList = Array<MutableList<Int>>(n) { mutableListOf() }
        val visited = HashSet<Int>()

        for ((i, j) in edges) {
            adjList[i]!!.add(j)
            adjList[j]!!.add(i)
        }

        fun dfs(v: Int = 0, parent: Int = -1): Boolean {
            if (v in visited) return false

            visited.add(v)

            for (next in adjList[v]!!) {
                if (next == parent) continue
                if (dfs(next, v).not()) return false
            }

            return true
        }

        return dfs() && visited.size == n
    }
}
