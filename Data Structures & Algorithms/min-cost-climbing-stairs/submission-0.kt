class Solution {
    fun minCostClimbingStairs(cost: IntArray): Int {
        return solution(cost)
    }

    private fun solution(cost: IntArray): Int {
        val cache = IntArray(cost.size + 1)

        for (i in 2 .. cost.size) {
            cache[i] = min(cache[i-1] + cost[i-1], cache[i-2] + cost[i-2])
        }

        return cache.last()
    }
}
