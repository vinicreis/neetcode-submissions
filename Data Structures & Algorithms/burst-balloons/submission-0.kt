class Solution {
    /**

        Edge cases:
            - nums is empty, return 0
            - nums.size == 1, return nums.first()

     */
    fun maxCoins(nums: IntArray): Int {
        if (nums.size == 0) return 0
        if (nums.size == 1) return nums[0]

        return divideAndConquer(nums)
    }

    /**

        nums = [ 4, 2, 3, 7 ]

                             l        r
                                l  r  
        balloons = [ 1 ] + [ 4, 2, 3, 7 ] + [ 1 ]

     */
    private fun divideAndConquer(nums: IntArray): Int {
        val balloons = intArrayOf(1) + nums + intArrayOf(1)
        val n = balloons.size
        val memo = Array(n) { IntArray(n) { -1 } }
        
        fun dfs(l: Int = 1, r: Int = n - 2): Int {
            if (l > r) return 0
            if (memo[l][r] > -1) return memo[l][r]

            for (i in l .. r) {
                val coins = balloons[l - 1] * balloons[i] * balloons[r + 1]
                
                memo[l][r] = max(memo[l][r], coins + dfs(l, i - 1) + dfs(i + 1, r))
            }

            return memo[l][r]
        }

        return dfs()
    }

    /**

        nums   = [ 4, 2, 3, 7 ]
        popped = [ f, f, f, f ]

        for each ballon, we can
            either pop it
            or skip it

        make a recursion (dfs - backtracking)
        considering current balloon
        passing popped ballons
        take the maximum deciding ih popped or not popped

     */
    // private fun intuition(nums: IntArray): Int {
    //     val n = nums.size

    //     fun dfs(
    //         i: Int = 0,
    //         popped: BooleanArray = BooleanArray(n),
    //     ): Int {
    //         if (popped.all { it }) return 0
    //         if (i > n - 1) return 0
            
    //         var prev = i - 1
    //         var next = i + 1

    //         while (prev >= 0 && popped[prev]) prev--
    //         while (next <= popped.lastIndex && popped[next]) next++

    //         val prevScore = nums.getOrDefault(prev, 1)
    //         val nextScore = nums.getOrDefault(next, 1)

    //         popped[i] = true
            
    //         var result = prevScore * nums[i] * nextScore + dfs(i + 1, popped)

    //         popped[i] = false

    //         result = max(result, dfs(i + 1, popped))

    //         return result
    //     }

    //     return dfs()
    // }

    // private fun IntArray.getOrDefault(i: Int, default: Int): Int {
    //     return if (i < 0 || i > lastIndex) default else this[i]
    // }
}
