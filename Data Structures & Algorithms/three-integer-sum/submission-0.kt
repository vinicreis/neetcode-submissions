class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        return solution1(nums)
    }

    /**
        Examples:
            nums = [ ]

            nums = [ -2, 2, 0, 10, 10, -20 ]
                   
                     i   l              j
                   [ -2, 2, 0, 10, 10, -20 ]

                      i     l   ...     j
                   [ -2, 2, 0, 10, 10, -20 ]

                         i  l ...  j
                   [ -2, 2, 0, 10, 10, -20 ]

                      i   l              j
                      i   l              j
                           i  l          j
                   [ -20, -2, 0, 2, 10, 10 ]

                    iterate i in nums.indices 
                        l = i + 1
                        j = nums.lastIndex
                        
                        when {
                            nums[i] + nums[l] + nums[j] < 0 -> l++
                            nums[i] + nums[l] + nums[j] > 0-> j--
                            else -> 
                                results.add(nums[i], nums[j], nums[l])
                                l++
                                j--

                                while l < j && nums[l] == nums[l-1]: l++
                        }

            result = [ [-20, 10, 10], [ -2, 0, 2 ] ]
     */
    private fun solution1(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) return emptyList()

        val results = mutableListOf<List<Int>>()
        val sorted = nums.sorted()

        for (i in sorted.indices) {
            if (sorted[i] > 0) break
            if (i > 0 && sorted[i] == sorted[i - 1]) continue

            var l = i + 1
            var j = sorted.lastIndex

            while (l < j) {
                val sum = sorted[i] + sorted[l] + sorted[j]

                when {
                    sum < 0 -> l++
                    sum > 0 -> j--
                    else -> {
                        results.add(listOf(sorted[i], sorted[l], sorted[j]))
                        l++
                        j--

                        while (l < j && sorted[l] == sorted[l-1]) l++
                    }
                }
            }
        }

        return results
    }
}
