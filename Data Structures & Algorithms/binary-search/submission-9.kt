class Solution {
    fun search(nums: IntArray, target: Int): Int {
        // println("Result: ${listOf(1, 2, 3, 4).binarySearch(2)}\n---")
        // println("Result: ${listOf(1, 2, 3, 4).binarySearch(3)}\n---")
        // println("Result: ${listOf(1, 2, 3, 4).binarySearch(4)}\n---")
        // println("Result: ${listOf(1, 2, 3, 4).binarySearch(10)}\n---")
        // println("Result: ${listOf(1, 2, 3, 4, 5, 6, 7).binarySearch(3)}\n---")
        // println("Result: ${listOf(1, 2, 3, 4, 5, 6, 7).binarySearch(10)}\n---")

        return nums.binarySearchI(target)
    }

    /**
        In O(logn) we can apply a binary search
        If the array is sorted, we can start at the middle looking for the target.

        If middle element > repeat the process on the right half
        otherwise, repeat the process on the left half

        target = 4
        5 / 2 = 2

        1
        [ 1, 2, 3, 4 ]
     */
    private fun IntArray.binarySearch(target: Int, from: Int = 0, to: Int = lastIndex): Int {
        if (from > to) return -1

        val index = from + (to - from) / 2
        val found = this[index]

        return when {
            found > target -> binarySearch(target, from = from, to = index - 1)
            found < target -> binarySearch(target, from = index + 1, to = to)
            else -> index
        }
    }


    private fun IntArray.binarySearchI(target: Int): Int {
        var from: Int = 0
        var to: Int = lastIndex

        while (from <= to) {
            val index = from + (to - from) / 2
            val found = this[index]

            println("From $from to $to")

            when {
                found > target -> to = index - 1
                found < target -> from = index + 1
                else -> return index
            }
        }

        return -1
    }
}
