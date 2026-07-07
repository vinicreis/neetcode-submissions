class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        return solution1(nums)
    }

    /**
        - Examples and edge cases:
            - Empty numbers? Empty array
            - 1 element on numbers? ~[ 0 ] or [ -1 ]~ might mislead, keep [ ]
            - 
                nums =      [  1,  2, 3, 4 ]
                result =    [ 24, 12, 8, 6 ]

                nums =      [ 0,  1, 2, 3, 4 ]
                result =    [ 24, 0, 0, 0, 0 ]

        - Approach 1:
            - initialize result as a copy
            - Calculate total product, without zeros
            - Map result where, for each value
                - product / value
            - return result
     */
    private fun solution1(nums: IntArray): IntArray {
        val result = IntArray(nums.size) { nums[it] }
        val product = nums.filter { it != 0 }
            .fold(1) { acc, value -> acc * value }
        val zeroCount = nums.count { it == 0 }

        nums.forEachIndexed { i, value ->
            result[i] = when {
                zeroCount > 1 -> 0
                zeroCount == 1 && result[i] != 0 -> 0
                result[i] != 0 -> product / result[i]
                else -> product
            }
        }

        return result
    }
}
