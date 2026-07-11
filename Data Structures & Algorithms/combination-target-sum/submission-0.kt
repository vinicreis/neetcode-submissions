class Solution {
    fun combinationSum(nums: IntArray, target: Int): List<List<Int>> {
        return solution(nums, target)
    }
    
    private fun solution(nums: IntArray, target: Int): List<List<Int>> {
        if (nums.isEmpty()) return emptyList()

        val result = mutableListOf<List<Int>>()

        fun dfs(i: Int = 0, sum: MutableList<Int> = mutableListOf(), total: Int = 0) {
            if (total == target) { result.add(ArrayList(sum)); return }
            if (i > nums.lastIndex || total > target) return

            sum.add(nums[i])
            dfs(i, sum, total + nums[i])
            sum.removeAt(sum.lastIndex)
            dfs(i + 1, sum, total)
        }

        dfs()

        return result
    }
}
