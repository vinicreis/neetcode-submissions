class Solution {
    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        return solution(edges)
    }
    
    /**
        edges are empty, return empty

        initialize adjacency list

        for each edge, from end to beginning
            remove on edge from graph
            look for a cycle
            if cycle, return edge
            add edge back to graph 

     */
    private fun solution(edges: Array<IntArray>): IntArray {
        if (edges.isEmpty()) return intArrayOf()

        val n = edges.size
        val adjList = Array<MutableList<Int>>(n + 1) { mutableListOf() }

        fun dfs(i: Int = 0, parent: Int = -1, visited: BooleanArray = BooleanArray(n + 1)): Boolean {
            if (visited[i]) return true

            visited[i] = true

            for (next in adjList[i]) {
                if (next == parent) continue
                if (dfs(next, i, visited)) return true
            }

            return false
        }

        for ((i, j) in edges) {
            adjList[i].add(j)
            adjList[j].add(i)

            if (dfs(i)) return intArrayOf(i, j)
        }

        return intArrayOf()
    }
}
