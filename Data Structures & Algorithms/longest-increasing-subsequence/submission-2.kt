class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        return dp1DSpace(nums)
    }
    
    private fun dp1DSpace(nums: IntArray): Int {
        // Time:  O(n²)
        // Space: O(n)
        if (nums.size <= 1) return nums.size

        val memo = IntArray(nums.size) { -1 }
        var result = 1

        fun dfs(i: Int = 0): Int { // O(n)
            if (memo[i] >= 0) return memo[i]

            var result = 1

            for (j in i + 1 until nums.size) {
                if (nums[j] > nums[i]) {
                    result = max(result, 1 + dfs(j))
                }
            }

            memo[i] = result

            return result
        }

        for (i in nums.indices) { // O(n²)
            result = max(result, dfs(i)) // O(n)
        }

        return result
    }

    private fun dpSquareSpace(nums: IntArray): Int {
        // Time:  O(n²)
        // Space: O(n²)
        if (nums.size <= 1) return nums.size

        val memo = Array(nums.size + 1) { IntArray(nums.size + 1) { -1 } }

        fun dfs(current: Int = 0, previous: Int = -1): Int { // O(n²)
            if (current > nums.lastIndex) return 0
            if (memo[current][previous.inc()] >= 0) return memo[current][previous.inc()]

            var result = dfs(current + 1, previous) // O(n)

            if (previous < 0 || nums[previous] < nums[current]) {
                result = max(result, 1 + dfs(current + 1, current)) // O(n)
            }

            memo[current][previous.inc()] = result

            return result
        }

        return dfs()
    }

    /**

        [ 9, 1, 4, 2, 3, 3, 7 ]
         [9], [1], [4] ... [7]

     */
    private fun recursion(nums: IntArray): Int {
        // Time:  O(n²)
        // Space: O(n)
        if (nums.size <= 1) return nums.size

        fun dfs(current: Int = 0, previous: Int = -1): Int { // O(n²)
            if (current > nums.lastIndex) return 0

            // println("Evaluating indexes $current vs $previous")

            var result = dfs(current + 1, previous) // O(n)

            if (previous < 0 || nums[previous] < nums[current]) {
                result = max(result, 1 + dfs(current + 1, current)) // O(n)
            }

            return result
        }

        return dfs()
    }
}
