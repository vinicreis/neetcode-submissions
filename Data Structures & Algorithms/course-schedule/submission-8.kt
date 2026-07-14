class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        return intuition(numCourses, prerequisites)
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
    private fun intuition(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // Time: O(V + E)
        // where
        //     len(prerequisites) = E
        if (numCourses <= 0) return true

        val pendings = hashMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Int>()

        repeat(numCourses) { course -> // O(E)
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
            
            pendings.remove(course)

            for ((next, requisites) in pendings) { // O(V)
                requisites.remove(course)
                
                if (requisites.isEmpty()) {
                    queue.add(next)
                }
            }

            if (pendings.isEmpty()) return true
        }

        return false
    }
}
