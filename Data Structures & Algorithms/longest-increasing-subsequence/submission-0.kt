class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        return recursion(nums)
    }
    
    /**

        [ 9, 1, 4, 2, 3, 3, 7 ]
         [9], [1], [4] ... [7]

     */
    private fun recursion(nums: IntArray): Int {
        // Time:  O(n²)
        // Space: O(n)
        if (nums.size <= 1) return nums.size

        fun dfs(current: Int = 0, previous: Int = -1): Int { // O(n²)
            if (current > nums.lastIndex) return 0

            // println("Evaluating indexes $current vs $previous")

            var result = dfs(current + 1, previous) // O(n)

            if (previous < 0 || nums[previous] < nums[current]) {
                result = max(result, 1 + dfs(current + 1, current)) // O(n)
            }

            return result
        }

        return dfs()
    }
}
