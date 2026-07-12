class Solution {
    fun rob(nums: IntArray): Int {
        return solution(nums)
    }

    private fun solution(nums: IntArray): Int {
        if (nums.size <= 0) return 0
        if (nums.size == 1) return nums.first()

        val cache = IntArray(nums.size)

        cache[0] = nums[0]
        cache[1] = nums[1]

        for (i in 2 until nums.size) {
            cache[i] = max(cache[i - 1], cache[i - 2] + nums[i])
        }

        return cache.last()
    }
}
