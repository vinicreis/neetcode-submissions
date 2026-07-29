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
        val memo = hashMapOf<Pair<Int, Boolean>, Int>()

        /**
            @param i price index
            @param buying indicated if allowed to buy current stock
         */
        fun dfs(i: Int = 0, buying: Boolean = true): Int {
            if (i > prices.lastIndex) return 0
            if ((i to buying) in memo) memo[i to buying]!!

            val cooldown = dfs(i + 1, buying)

            memo[i to buying] = if (buying) {
                max(dfs(i + 1, false) - prices[i], cooldown)
            } else {
                max(dfs(i + 2, true) + prices[i], cooldown)
            }

            return memo[i to buying]!!
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
