class Solution {
    /**

        You are given an array of integers nums and an integer k. 
        
        There is a sliding window of size k that starts at the left edge of the array. 
        
        The window slides one position to the right until it reaches the right edge of the array.

        Return a list that contains the maximum element in the window at each step.

     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        return dynamicProgramming(nums, k)
    }

    private fun dynamicProgramming(nums: IntArray, k: Int): IntArray {
        if (k <= 0) return intArrayOf()
        if (nums.size < k) return intArrayOf()

        val leftMax = IntArray(nums.size) { Int.MIN_VALUE }
        val rightMax = IntArray(nums.size) { Int.MIN_VALUE }

        leftMax[0] = nums[0]
        rightMax[nums.lastIndex] = nums.last()

        for (i in 1 until nums.size) {
            if (i % k == 0) {
                leftMax[i] = nums[i]
            } else {
                leftMax[i] = max(leftMax[i-1], nums[i])
            }

            if ((nums.lastIndex - i) % k == 0) {
                rightMax[nums.lastIndex - i] = nums[nums.lastIndex - i]
            } else {
                rightMax[nums.lastIndex - i] = max(rightMax[nums.size - i], nums[nums.lastIndex - i])
            }
        }

        val result = IntArray(nums.size - k + 1)

        for (i in 0 .. (nums.size - k)) {
            result[i] = max(leftMax[i + k - 1], rightMax[i])
        }

        return result
    }

    /**
        k = 3
        [ 1, 2, 3, 4, 5, 6 ]

                 [ [1, 2, 3], 4, 5, 6 ]
                 [ 1, [2, 3, 4], 5, 6 ]
                 [ 1, 2, [3, 4, 5], 6 ]
                 [ 1, 2, 3, [4, 5, 6] ]
        result = [ 3, 4, 5, 6 ]

        size = nums.size - k + 1
     */
    private fun bruteForce(nums: IntArray, k: Int): IntArray {
        if (k <= 0) return intArrayOf()
        if (nums.size < k) return intArrayOf()

        val resultSize = nums.size - k + 1
        val result = IntArray(resultSize)
        var i = 0
        var j = k

        while (j <= nums.size) {
            val sublist = nums.slice(i until j)

            // println(sublist)
            
            result[i] = sublist.max()
            i++; j++;
        }

        return result
    }
}
