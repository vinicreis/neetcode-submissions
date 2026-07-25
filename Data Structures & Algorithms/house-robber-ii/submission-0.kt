class Solution {
    fun rob(nums: IntArray): Int {
        return solution(nums)
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
           
        [ 0, 9, 9, 12, 12 ]

     */
    private fun solution(nums: IntArray): Int {
        val n = nums.size

        if (n <= 0) return 0
        if (n == 1) return nums[0]
        if (n == 2) return max(nums[0], nums[1])
        if (n == 3) return maxOf(nums[0], nums[1], nums[2])

        val memo = IntArray(n + 1)
        var i = 3

        memo[1] = nums[1]
        memo[2] = max(nums[1], nums[2])
        
        while (i < n) {
            memo[i] = max(memo[i - 1], nums[i] + memo[i - 2])

            i++
        }

        memo[i] = max(nums[0] + memo[i - 2], memo[i - 1])

        println("Memo: ${memo.toList()}")

        return memo.last()
    }
}
