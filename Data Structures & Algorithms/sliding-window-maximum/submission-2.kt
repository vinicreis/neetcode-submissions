class Solution {
    /**

        You are given an array of integers nums and an integer k. 
        
        There is a sliding window of size k that starts at the left edge of the array. 
        
        The window slides one position to the right until it reaches the right edge of the array.

        Return a list that contains the maximum element in the window at each step.

     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        return solution(nums, k)
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
    private fun solution(nums: IntArray, k: Int): IntArray {
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
