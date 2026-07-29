class Solution {
    /**

        Edge cases
            - prices.size <= 1, return 0     

     */
    fun maxProfit(prices: IntArray): Int {
        if (prices.size <= 1) return 0
            
        return dp(prices)
    }

    private fun dp(prices: IntArray): Int {
        val n = prices.size
        val memo = Array(n) { IntArray(n) { -1 } }

        /**
            @param i price index
            @param buying indicated if allowed to buy current stock
         */
        fun dfs(i: Int = 0, bought: Int? = null): Int {
            if (i > prices.lastIndex) return 0
            if (bought != null && memo[i][bought] > -1) return memo[i][bought]

            val cooldown = dfs(i + 1, bought)
            val profit = if (bought == null) {
                max(dfs(i + 1, i) - prices[i], cooldown)
            } else {
                memo[i][bought] = max(dfs(i + 2, null) + prices[i], cooldown)

                memo[i][bought]
            }

            return profit
        }

        return dfs()
    }

    /**

        [ 1, 3, 4, 0, 4 ]

        - Decisions
            1. If not bought, buy or not buy
            2. if bought, sell with cooldown or not sell

        recurse taking the max of these decisions
        on depth, so dfs

     */
    private fun dfs(prices: IntArray): Int {
        /**
            @param i price index
            @param buying indicated if allowed to buy current stock
         */
        fun dfs(i: Int = 0, buying: Boolean = true): Int {
            if (i > prices.lastIndex) return 0

            val cooldown = dfs(i + 1, buying)
            val profit = if (buying) {
                max(dfs(i + 1, false) - prices[i], cooldown)
            } else {
                max(dfs(i + 2, true) + prices[i], cooldown)
            }

            return profit
        }

        return dfs()
    }
}
