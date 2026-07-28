class Solution {
    fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        return slidingWindow(nums)
    }

    /**
        Edge cases:
            - nums is empty, return 0
        i,j
         i j
             ij
        [2,4,-3,5]
     */
    private fun slidingWindow(nums: IntArray): Int {
        var i = 0
        var j = 0

        var result = Int.MIN_VALUE

        while (i <= j && j <= nums.lastIndex) {
            val sublist = nums.slice(i .. j)
            val sum = sublist.fold(1) { acc, num -> acc * num }

            println(sublist)

            when {
                sum <= result && i < j -> i++
                sum < result -> j++
                else -> {
                    result = sum
                    j++
                }
            }
        }

        return result
    }
}
