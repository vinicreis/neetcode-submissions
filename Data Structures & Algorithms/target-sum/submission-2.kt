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
        val memo = hashMapOf<Pair<Int, Int>, Int>()

        fun dfs(i: Int = 0, remaining: Int = target): Int {
            if (i == nums.size && remaining == 0) return 1
            if (i > nums.lastIndex) return 0
            if ((nums[i] to remaining) in memo) return memo[nums[i] to remaining]!!

            memo[nums[i] to remaining] = dfs(i + 1, remaining + nums[i]) + dfs(i + 1, remaining - nums[i])

            return memo[nums[i] to remaining]!!
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
