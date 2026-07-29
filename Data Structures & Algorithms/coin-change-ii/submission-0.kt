class Solution {
    /**

        Edge cases
            - coins is empty, return 0
            - amount == 0, return 0

     */
    fun change(amount: Int, coins: IntArray): Int {
        if (amount == 0) return 0
        if (coins.isEmpty()) return 0

        return recursion(amount, coins)
    }
    
    /**

        for each coin, we can choose to pick this coin or not
        if valid result, increment
        set a target, which will be the remaining amount
        recursion?

     */
    private fun recursion(amount: Int, coins: IntArray): Int {
        fun dfs(i: Int = 0, target: Int = amount): Int {
            if (target == 0) return 1
            if (i > coins.lastIndex) return 0

            return dfs(i + 1, target) + if (target >= coins[i]) { 
                dfs(i, target - coins[i])
            } else 0
        }

        return dfs()
    }
}
