class Solution {
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        return dfs(n, edges)
    }

    /**

        build adjacency list
        a global visited set

        dfs(v, parent)
            if visited, return

            add v to visited

            for node in edges
                if node == parent, continue
                dfs(node, v)
        
        for each vertice
            if not visited
                start a dfs
                every time we leave, 1 more component

        return component

     */
    private fun dfs(n: Int, edges: Array<IntArray>): Int {
        if (n <= 0) return 0
        if (n == 1) return 1

        val adjList = Array<MutableList<Int>>(n) { mutableListOf() }
        val visited = HashSet<Int>()
        var result = 0

        for ((i, j) in edges) {
            adjList[i].add(j)
            adjList[j].add(i)
        }

        fun dfs(v: Int, parent: Int = -1) {
            if (v in visited) return

            visited.add(v)

            for (next in adjList[v]) {
                if (next == parent) continue

                dfs(next, v)
            }
        }

        for (v in 0 until n) {
            if (v !in visited) {
                dfs(v)
                result++
            }
        }

        return result
    }
}
