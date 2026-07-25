class Solution {
    fun rob(nums: IntArray): Int {
        return dp(nums)
    }

    /**
        n = nums.size

        Edge cases:
            n == 0, return 0
            n == 1, return nums[0]
            n == 2, return max(nums[0], nums[1])

        r = 15
            i
        [ 2, 9, 8, 3 ]
           
        [ 0, 9, 9, 12 ]

     */
    private fun dp(nums: IntArray): Int {
        if (nums.size <= 0) return 0
        if (nums.size == 1) return nums[0]

        fun impl(houses: List<Int>): Int {
            val n = houses.size

            if (n <= 0) return 0
            if (n == 1) return houses[0]

            val memo = IntArray(n)

            memo[0] = houses[0]
            memo[1] = max(houses[0], houses[1])

            for (i in 2 until n) {
                memo[i] = max(memo[i - 1], houses[i] + memo[i - 2])
            }

            return memo.last()
        }

        return max(impl(nums.drop(1)), impl(nums.dropLast(1)))
    }
}
