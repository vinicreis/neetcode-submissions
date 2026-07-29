class Solution {
    /**

        Edge cases
            - if nums is empty, return 0

     */
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0

        return dp(nums, target)
    }

    private fun dp(nums: IntArray, target: Int): Int {
        // Time:  O(2^n)
        // Space: O(n)
        val total = nums.sum()
        val memo = Array(nums.size) { IntArray(total * 2 + 1) { Int.MIN_VALUE } }

        fun dfs(i: Int = 0, t: Int = 0): Int {
            if (i == nums.size && t == target) return 1
            if (i > nums.lastIndex) return 0
            if (memo[i][t + total] > Int.MIN_VALUE) return memo[i][t + total]

            memo[i][t + total] = dfs(i + 1, t + nums[i]) + dfs(i + 1, t - nums[i])

            return memo[i][t + total]
        }

        return dfs()
    }

    /**

        - for each coin
            - sum coin
            - minus coin

        when reach target, increment
        recursion? dfs?

     */
    private fun intuition(nums: IntArray, target: Int): Int {
        // Time:  O(2^n)
        // Space: O(n)
        fun dfs(i: Int = 0, remaining: Int = target): Int {
            if (i == nums.size && remaining == 0) return 1
            if (i > nums.lastIndex) return 0

            return dfs(i + 1, remaining + nums[i]) + dfs(i + 1, remaining - nums[i])
        }

        return dfs()
    }
}
