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
        if (numCourses <= 0) return true
        if (prerequisites.isEmpty()) return true

        val finished = hashSetOf<Int>()
        val pendings = hashMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Int>()

        prerequisites.flatMap { it.toList() }.forEach { course ->
            pendings[course] = mutableListOf()
        }

        for ((requisite, course) in prerequisites) {
            pendings[course] = pendings.getOrDefault(course, mutableListOf()).also { requisites ->
                requisites.add(requisite)
            }
        }

        for ((course, requisites) in pendings) {
            if (requisites.isEmpty()) {
                queue.add(course)
            }
        }

        // println("Pendings: $pendings")
        // println("Queue: $queue\n")

        while (queue.isNotEmpty()) {
            val course = queue.removeLast()

            finished.add(course)
            pendings.remove(course)

            // println("Pendings: $pendings")
            // println("Queue: $queue\n")
            // println("Finished: $finished")

            if (finished.size == numCourses) return true

            for ((course, requisites) in pendings) {
                if (requisites.isEmpty() || requisites.all { it in finished }) {
                    queue.add(course)
                }
            }
        }

        return false
    }
}
