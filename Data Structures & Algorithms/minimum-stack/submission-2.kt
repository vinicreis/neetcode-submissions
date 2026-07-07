/**
    Stacks are FILO (first-in, last-out), so
        - push: adds an element
        - pop: remove the last pushed element
        - top: get the last pushed element
        - getMin: get the value with lowest value
    All done in O(n)

    Space: O(n)
 */
class MinStack() {
    private val data = ArrayDeque<Int>()
    private val mins = ArrayDeque<Int>()

    fun push(`val`: Int) {
        data.push(`val`)
        
        if (mins.isEmpty()) {
            `val`
        } else {
            min(`val`, mins.peek())
        }.also(mins::push)
    }

    fun pop() {
        data.pop()
        mins.pop()
    }

    fun top(): Int = data.peek()

    fun getMin(): Int = mins.peek()
}
