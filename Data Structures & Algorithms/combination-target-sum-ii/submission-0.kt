class Solution {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        return solution(candidates, target)
    }

    private fun solution(candidates: IntArray, target: Int): List<List<Int>> {
        if (candidates.isEmpty()) return emptyList()

        val result = mutableListOf<List<Int>>()
        
        candidates.sort()

        fun dfs(i: Int, selected: MutableList<Int>, total: Int) {
            if (total == target) { result.add(ArrayList(selected)); return }
            if (i > candidates.lastIndex || total > target) return

            selected.add(candidates[i])
            dfs(i + 1, selected, total + candidates[i])
            selected.removeAt(selected.lastIndex)

            var next = i + 1
            while (next <= candidates.lastIndex && candidates[i] == candidates[next]) {
                next++
            }

            dfs(next, selected, total)
        }

        dfs(0, mutableListOf(), 0)

        return result
    }
}
