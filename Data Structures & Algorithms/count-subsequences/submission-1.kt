class Solution {
    /**

        - Edge cases
            - t is empty, return 1
            - s is empty, return 0

     */
    fun numDistinct(s: String, t: String): Int {
        if (t.isEmpty()) return 0
        if (s.isEmpty()) return 0

        return intuition(s, t)
    }
    

    /**

        To find all subsequences, we can make a kind of backtracking
        We use the choices, for each character on s:
            1. Add char
            2. Skip char

        for a current text cur:
            if (cur == t) return 1
            if (cur.length != t.length) return 0
            if (i > s.length) return 0


        Use a recursion to find subsequence?

     */
    private fun intuition(s: String, t: String): Int {
        fun dfs(i: Int = 0, cur: String = ""): Int {
            // println("Current: $cur")
            if (cur == t) return 1
            if (cur.length > t.length) return 0
            if (i > s.lastIndex) return 0

            return dfs(i + 1, cur) + dfs(i + 1, cur + s[i])
        }

        return dfs()
    }
}
