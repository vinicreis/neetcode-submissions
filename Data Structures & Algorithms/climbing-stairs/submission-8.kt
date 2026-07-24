class Solution {
    fun climbStairs(n: Int): Int {
        return dp(n)
    }

    /**

        [ 1, 2, ..., n ]
          0, 1, ..., n + 1

     */
    private fun dp(n: Int): Int {
        if (n < 0) return 0

        val memo = IntArray(n + 1) { 0 }

        memo[1] = 1
        memo[2] = 2

        for (i in 2 .. n) {
            memo[i] = memo[i - 1] + memo[i - 2]
        }

        return memo[n - 1]
    }

    /**

        Edge cases:
            - n <= 0, return 0
            - n == 1, return 1
            - n == 2, return 2

        Recursion
            n, n - 1, ..., 0

            solution(n - 1) + solution(n - 2)

     */
    private fun recursion(n: Int): Int {
        if (n <= 0) return 0
        if (n == 1) return 1
        if (n == 2) return 2

        return recursion(n - 1) + recursion(n - 2)
    }
}
