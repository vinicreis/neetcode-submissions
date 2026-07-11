class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        return dfs(nums)
    }

    private fun dfs(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) return emptyList()

        val result = mutableListOf<List<Int>>()
        val subset = mutableListOf<Int>()

        fun dfs(i: Int = 0) {
            if (i == nums.size) {
                result.add(subset.toList()); return
            }

            subset.add(nums[i])
            dfs(nums, i + 1)
            subset.removeAt(subset.size - 1)
            dfs(nums, i + 1)
        }

        dfs()

        return result
    }
}
