class Solution {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        return solution(n, edges)
    }

    /**



     */
    private fun solution(n: Int, edges: Array<IntArray>): Boolean {
        val adjList = HashMap<Int, MutableList<Int>>()

        for (v in 0 until n) {
            adjList[v] = mutableListOf()
        }

        for (edge in edges) {
            val (i, j) = edge

            adjList[i]!!.add(j)
            // adjList[j]!!.add(i)
        }

        println("Adjacency list: $adjList")

        fun dfs(v: Int, visited: HashSet<Int>): Boolean {
            if (v in visited) return false

            // println("Now on vertice $v")

            visited.add(v)

            for (next in adjList[v]!!) {
                if (dfs(next, visited).not()) return false
            }

            return true
        }

        for (v in adjList.keys) {
            val visited = HashSet<Int>()

            if (dfs(v, visited).not()) return false

            // println("No cycle for vertice $v")
        }

        return true
    }
}
