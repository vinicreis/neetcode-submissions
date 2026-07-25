class Solution {
    fun rob(nums: IntArray): Int {
        return recursion(nums)
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
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums.first()

        fun dfs(i: Int): Int {
            if (i > nums.lastIndex) return 0

            return max(nums[i] + dfs(i + 2), dfs(i + 1))
        }

        return max(dfs(0), dfs(1))
    }
}
