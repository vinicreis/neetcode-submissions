class Solution {
    fun minCostClimbingStairs(cost: IntArray): Int {
        return dp(cost)
    }

    /**

        [ 1, 2, 3 ]
                   v
        [ 0, 0, 1, 2 ]

        [ 1, 2, 1, 2, 1, 1, 1 ]
                               v
        [ 0, 0, 1, 2, 2, 3, 3, 4 ]

     */
    private fun dp(cost: IntArray): Int {
        if (cost.isEmpty()) return 0
        if (cost.size == 1) return cost.first()

        val memo = IntArray(cost.size + 1) { 0 }

        for (i in 2 .. cost.size) {
            memo[i] = min(memo[i - 1] + cost[i - 1], memo[i - 2] + cost[i - 2])
        }

        return memo.last()
    }

    /**
          v  v  v  
        [ 1, 2, 3 ]
        r = 1 + min(min(), min())

        cost is empty, return 0
        Base case: cost.size == 1, return cost[0]
        General case: cost.size == 2, return, for i: result = min(cost[i - 2], cost[i - 1])

        Solved with recursion, with time complexity of 2^n
     */
    private fun recursion(cost: IntArray): Int {
        if (cost.isEmpty()) return 0
        if (cost.size == 1) return cost.first()

        fun recursion(i: Int = 0): Int {
            if (i > cost.lastIndex) return 0

            return cost[i] + min(recursion(i + 1), recursion(i + 2))
        }

        return min(recursion(0), recursion(1))
    }
}
