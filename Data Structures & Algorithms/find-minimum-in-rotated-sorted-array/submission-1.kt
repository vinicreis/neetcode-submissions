class Solution {
    /**

        You are given an array of length n which was originally sorted in ascending order. 
        It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:

        [3,4,5,6,1,2] if it was rotated 4 times.
        [1,2,3,4,5,6] if it was rotated 6 times.
        
        Notice that rotating the array 4 times moves the last four elements of the array to the beginning. 
        Rotating the array 6 times produces the original array.

        Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.

        A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?

     */
    fun findMin(nums: IntArray): Int {
        return solution(nums)
    }

    /**

        O (log n) indicates a binary search.

        Sort is an option? If is, trivial, common binary search.

        Probably not, sorting would take O(n) with the best algorithm.

        [3,4,5,6,1,2]

        iteration binary search

        var from = 0
        var to = nums.lastIndex
        var minimum = Int.M_VALUE

        while (from <= to) {
            val index = from + (to - from) / 2
            val found = nums[index]
            
            minimum = min(minimum, found)

            when {
                nums[from] < nums[to] -> to = index - 1
                nums[from] > nums[to] -> from = index + 1
            }
        }

        return minimum
     */
    private fun solution(nums: IntArray): Int {
        var from = 0
        var to = nums.lastIndex
        var minimum = Int.MAX_VALUE

        while (from <= to) {
            val index = from + (to - from) / 2
            val found = nums[index]
            
            minimum = min(minimum, found)

            var leftMin = Int.MAX_VALUE
            for (i in from until index) {
                leftMin = min(leftMin, nums[i])
            }

            var rightMin = Int.MAX_VALUE
            for (i in (index + 1) .. to) {
                rightMin = min(rightMin, nums[i])
            }

            when {
                rightMin > leftMin -> to = index - 1
                else -> from = index + 1
            }
        }

        return minimum
    }
}
