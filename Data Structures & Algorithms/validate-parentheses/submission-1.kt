class Solution {
    fun isValid(s: String): Boolean {
        return solution1(s)
    }

    /**
        - Edge cases:
            - "" = true
            
        - Solved with a stack
            - queue = LinkedList<Char>()
            - iterate from start
            - if opening bracket, add to the queue
            - if closing
                pop from queue
                if does not matches
                    return false
     */
    private fun solution1(s: String): Boolean {
        if (s.isBlank()) return true

        val queue = ArrayDeque<Char>()

        for (i in 0 until s.length) {
            // println("Queue: ${queue.toList()}")

            if (s[i] in Opening) {
                queue.push(s[i])

                continue
            }

            if (queue.isEmpty()) return false

            val queued = queue.pop()

            // println("Queued: ${queued}")
            // println("Current: ${s[i]}")

            if (queued.matches(s[i]).not()) {
                return false
            }
        }

        return queue.isEmpty()
    }

    private companion object {
        private val Opening = listOf('(', '[', '{')
        private val Closing = listOf(')', ']', '}')

        private fun Char.matches(other: Char): Boolean = when (this) {
            '(' -> other == ')'
            // ')' -> other == '('
            '[' -> other == ']'
            // ']' -> other == '['
            '{' -> other == '}'
            // '}' -> other == '{'
            else -> error("Character ${toString()} is not a bracket")
        }
    }
}
