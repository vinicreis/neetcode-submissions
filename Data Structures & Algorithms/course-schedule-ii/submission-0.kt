class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        return dfs(numCourses, prerequisites)
    }

    /**

        Traverse the graph
            DFS

        Starting from possible courses (no dependencies)
            scan prerequisites for pending map?

        Register path 
            use visited hashset?

        Detect cycle
            course apears on visited

     */
    private fun dfs(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        if (numCourses <= 0) return intArrayOf()

        val courses = HashMap<Int, MutableList<Int>>()
        val result = mutableListOf<Int>()
        val visited = hashSetOf<Int>()
        val cycle = hashSetOf<Int>()

        for (course in 0 until numCourses) {
            courses[course] = mutableListOf()
        }

        prerequisites.forEach { (course, requisite) ->
            courses[course]!!.add(requisite)
        }
        
        fun innerDfs(course: Int): Boolean {
            if (course in cycle) return false
            if (course in visited) return true

            cycle.add(course)

            for (next in courses[course].orEmpty()) {
                if (innerDfs(next).not()) return false
            }

            cycle.remove(course)
            result.add(course)
            visited.add(course)

            return true
        }

        repeat(numCourses) { course ->
            if (innerDfs(course).not()) return intArrayOf()
        }

        return result.toIntArray()
    }
}
