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

        val n = edges.maxOf { (i, j) -> max(i, j) }

        val adjList = Array<MutableList<Int>>(n) { mutableListOf() }

        edges.forEach { (i, j) ->
            adjList[i - 1].add(j - 1)
            adjList[j - 1].add(i - 1)
        }

        // println("Adjacency list: ${adjList.toList()}")

        fun dfs(i: Int = 0, visited: HashSet<Int>): Boolean {
            if (i in visited) return true

            visited.add(i)

            for (next in adjList[i]) {
                if (dfs(next, visited)) return true
            }

            return false
        }

        for (e in edges.lastIndex downTo 0) {
            val visited: HashSet<Int> = hashSetOf()
            val (i, j) = edges[e]
            val x = i - 1
            val y = j - 1

            adjList[x].remove(y)
            adjList[y].remove(x)

            for (v in 0 until n) {
                if (dfs(v, visited = visited) && visited.size == n) {
                    return intArrayOf(i, j)
                }
            }

            adjList[x].add(y)
            adjList[y].add(x)
        }

        return intArrayOf()
    }
}
