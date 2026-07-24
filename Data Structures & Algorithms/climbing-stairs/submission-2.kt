class Solution {
    fun climbStairs(n: Int): Int {
        return recursion(n)
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
