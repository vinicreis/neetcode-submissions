class Solution {
    fun climbStairs(n: Int): Int {
        return dp(n)
    }

    private fun dp(n: Int): Int {
        if (n <= 0) return 0
        if (n <= 2) return n

        val cache = IntArray(n) { -1 }

        fun dfs(i: Int = 0): Int {
            if (i == n) return 1
            if (i > n) return 0
            if (cache[i] != -1) return cache[i]

            cache[i] = dfs(i + 1) + dfs(i + 2)
        }

        return dfs()
    }
}
