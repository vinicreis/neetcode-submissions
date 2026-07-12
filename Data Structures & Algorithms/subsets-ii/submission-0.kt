class Solution {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        return solution(nums)
    }

    private fun solution(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) return emptyList()

        val result = mutableListOf<List<Int>>()

        nums.sort()

        fun dfs(
            i: Int = 0,
            selected: MutableList<Int> = mutableListOf(),
        ) {
            if (i == nums.size) {
                result.add(ArrayList(selected))
                return
            }

            selected.add(nums[i])
            dfs(i + 1, selected)
            selected.removeLast()

            var j = i
            while (j + 1 <= nums.lastIndex && nums[j] == nums[j + 1]) j++

            dfs(j + 1, selected)
        }

        dfs()

        return result
    }
}
