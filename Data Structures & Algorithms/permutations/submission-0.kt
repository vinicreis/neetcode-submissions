class Solution {
    fun permute(nums: IntArray): List<List<Int>> {
        return solution(nums)
    }

    private fun solution(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) return emptyList()

        val result = mutableListOf<List<Int>>()

        fun dfs(
            selected: MutableList<Int> = mutableListOf(), 
            picked: BooleanArray = BooleanArray(nums.size)
        ) {
            if (selected.size == nums.size) { 
                result.add(ArrayList(selected))
                return
            }

            for (i in nums.indices) {
                if (picked[i]) continue

                selected.add(nums[i])
                picked[i] = true
                dfs(selected, picked)
                selected.removeLast()
                picked[i] = false
            }
        }

        dfs()

        return result
    }
}
