class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        return solution(numCourses, prerequisites)
    }

    /**
        prerequisites = n
        n = V * E

        - Edge cases
            if no course is needed, return true

        O(n)
        compute a map with course as key, and required courses as values
            { 0: [ 1 ], 1: [] }
            { 0: [ 1 ], 1: [ 0 ] }

        O(n)
        compute a queue with starting courses, decrementing remaining courses
            [ 1 ] | remaining = 1
            []    | remaining = 2

        create a list of finished courses

        while queue is not empty
            val course = deque

            add to finished courses

            iterate courses map: (course, requirements) ->
                if all requirements in finished
                    remove course from map
                    decrement remaining
                    add to queue

                if remaining == 0, return true

        return false
     */
    private fun solution(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // Time: O(V + E)
        // where
        //     len(prerequisites) = E
        if (numCourses <= 0) return true

        val finished = hashSetOf<Int>()
        val pendings = hashMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Int>()

        prerequisites.flatMap { it.toList() }.forEach { course -> // O(E)
            pendings[course] = mutableListOf()
        }

        for ((requisite, course) in prerequisites) { // O(E)
            pendings[course] = pendings.getOrDefault(course, mutableListOf()).also { requisites ->
                requisites.add(requisite)
            }
        }

        for ((course, requisites) in pendings) { // O(V)
            if (requisites.isEmpty()) {
                queue.add(course)
            }
        }

        while (queue.isNotEmpty()) { // O(V)
            val course = queue.removeLast()
            
            finished.add(course)
            pendings.remove(course)

            for ((_, requisites) in pendings) { // O(V)
                requisites.remove(course)
            }

            for ((course, requisites) in pendings) { // O(V)
                if (requisites.isEmpty()) {
                    queue.add(course)
                }
            }

            if (finished.size == numCourses) return true
            if (pendings.isEmpty()) return true
        }

        return pendings.isEmpty()
    }
}
