class Solution {
    /**

        Edge cases:
            - nums.size == 0, return true
            - nums.size == 1, return false

     */
    fun canPartition(nums: IntArray): Boolean {
        if (nums.size == 0) return true
        if (nums.size == 1) return false
        if (nums.sum() % 2 == 1) return false

        return dp(nums)
    }

    private fun dp(nums: IntArray): Boolean {
        val total = nums.sum()
        val memo = Array(nums.size) { IntArray((total / 2) + 1) { -1 } }

        fun dfs(i: Int = 0, target: Int): Boolean {
            if (target == 0) return true
            if (target < 0 || i > nums.lastIndex) return false
            if (memo[i][target] >= 0) return memo[i][target] == 1

            val found = dfs(i + 1, target) || dfs(i + 1, target - nums[i])

            memo[i][target] = if (found) 1 else 0

            return found
        }

        return dfs(0, total / 2)
    }

    private fun intuition(nums: IntArray): Boolean {
        val total = nums.sum()

        fun dfs(i: Int = 0, target: Int): Boolean {
            if (target == 0) return true
            if (target < 0 || i > nums.lastIndex) return false

            return dfs(i + 1, target) || dfs(i + 1, target - nums[i])
        }

        return dfs(0, total / 2)
    }
}
