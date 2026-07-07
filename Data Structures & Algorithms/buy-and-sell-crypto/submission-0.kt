class Solution {
    /**

        You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.

        You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.

        Return the maximum profit you can achieve. 
        You may choose to not make any transactions, in which case the profit would be 0.

     */
    fun maxProfit(prices: IntArray): Int {
        return twoPointer(prices)
    }

    /**

        prices = [ 10, 20, 60, 10, 100 ]
        result = 100 - 10 = 90

        Edge cases:
            - prices are empty: 0 profit

        - traverse prices somehow, to achieve the maximum profit
        - profit is selling value - buying value
        - sorting is not an option
            - if I buy some day, I could only sell in a future day
        - some pointer approach?

              b   s 
            [ 10, 20, 60, 10, 100 ]

        - approach b to s, while b < s (<= not considered, buy and sell on same date, profit = 0)

        - if s has profit, compute max
        - else, b = s

        ++
     */
    private fun twoPointer(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        var b = 0
        var s = 1
        var maxProfit = 0

        while (s < prices.size) {
            if (prices[s] <= prices[b]) {
                b = s
            } else {
                maxProfit = max(maxProfit, prices[s] - prices[b])
            }

            s++
        }

        return maxProfit
    }
}
