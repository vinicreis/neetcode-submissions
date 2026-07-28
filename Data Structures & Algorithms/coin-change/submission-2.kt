class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        return bfs(coins, amount)
    }

    private fun bfs(coins: IntArray, amount: Int): Int {
        if (coins.isEmpty()) return -1

        val queue = ArrayDeque<Pair<Int, Int>>()

        queue.addFirst(0 to 0)

        while (queue.isNotEmpty()) {
            val (current, coinCount) = queue.removeLast()

            if (current == amount) return coinCount

            for (coin in coins) {
                if (current + coin <= amount) {
                    queue.addFirst((current + coin) to (coinCount + 1))
                }
            }
        }

        return -1
    }

    private fun dpTopDown(coins: IntArray, amount: Int): Int {
        if (coins.isEmpty()) return -1

        val memo = hashMapOf(0 to 0)

        fun rec(remaining: Int = amount): Int {
            if (remaining in memo) return memo[remaining]!!
            if (remaining == 0) return 0

            var result = INF
            
            for (coin in coins) {
                if (remaining - coin >= 0) {
                    result = min(result, 1 + rec(remaining - coin))
                }
            }

            memo[remaining] = result

            return result
        }

        return rec().takeIf { it < INF } ?: -1
    }

    /**

        Edge cases:
            - coins is empty, return -1
            - amount <= 0, return -1

        if coins is sorted, maybe we can choose where to go?
        if coins are sorted in desc order, helps the solution?

        amount = 12
        coins  = [ 1, 5, 10 ]

     */
    private fun recursion(coins: IntArray, amount: Int): Int {
        if (coins.isEmpty()) return -1

        fun rec(remaining: Int = amount): Int {
            if (remaining <= 0) return 0
            
            var result = INF
            
            for (coin in coins) {
                if (remaining - coin >= 0) {
                    result = min(result, 1 + rec(remaining - coin))
                }
            }

            return result
        }

        return rec().takeIf { it < INF } ?: -1
    }

    companion object {
        const val INF = 1000000000
    }
}
