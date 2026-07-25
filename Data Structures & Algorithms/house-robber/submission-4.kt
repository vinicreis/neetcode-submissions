class Solution {
    fun rob(nums: IntArray): Int {
        return dp(nums)
    }

    /**

        [ 2, 9,  8,  3,  6 ]
                         v
        [ 2, 9, 10, 12, 16 ]
        r = 16
     */
    private fun dp(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums.first()

        val memo = IntArray(nums.size)

        memo[0] = nums[0]
        memo[1] = max(nums[0], nums[1])

        for (i in 2 until nums.size) {
            memo[i] = max(nums[i] + memo[i - 2], memo[i - 1])
        }

        return memo.last()
    }

    /**

        Edge cases:
            - no houses? return 0
            - 1 house? return nums[0]

        [ 2, 9, 8, 3, 6 ]

        rec(i)
            if (i > nums.lastIndex) return 0

            return nums[i] + max(rec(i), rec(i + 1))

        max(rec(0), rec(1))

     */
    private fun recursion(nums: IntArray): Int {
        // Time:  O(2^n)
        // Space: O(1)
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums.first()

        fun dfs(i: Int): Int {
            if (i > nums.lastIndex) return 0

            return max(nums[i] + dfs(i + 2), dfs(i + 1))
        }

        return max(dfs(0), dfs(1))
    }
}
