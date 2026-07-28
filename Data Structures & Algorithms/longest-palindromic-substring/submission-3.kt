class Solution {
    fun longestPalindrome(s: String): String {
        return dp(s)
    }

    /**

        - Same idea, iterate middle and go to left and right
        - Memoizing: Use a matrix where cells (i, j) represent if is palindrome
        - 

     */
    private fun dp(s: String): String {
        if (s.length <= 1) return s

        val n = s.length
        val memo = Array<BooleanArray>(n) { BooleanArray(n) }
        var resultStart = 0
        var resultLength = 0

        for (i in s.lastIndex downTo 0) {
            for (j in i until n) {
                if (s[i] == s[j] && (j - i <= 2 || memo[i.inc()][j.dec()])) {
                    memo[i][j] = true

                    if ((j - i + 1) > resultLength) {
                        resultStart = i
                        resultLength = j - i + 1
                    } 
                }
            }
        }

        return s.substring(resultStart, resultStart + resultLength)
    }

    /**

        Edge cases:
            - s.length = 0, return s
            - s.length = 1, return s

         ij..j
         l ...l r...r
        "ddbbd"

     */
    private fun doublePointer(s: String): String {
        if (s.length <= 1) return s

        var maxLength = 0
        var indexes: Pair<Int, Int>? = null

        for (i in s.indices) {
            for (j in i until s.length) {
                var l = i
                var r = j

                while (l < r && s.isPalindrome(l, r)) {
                    l++; r--
                }

                if (l >= r && maxLength < (j - i + 1)) {
                    maxLength = j - i + 1
                    indexes = i to j.inc()
                }
            }
        }

        return indexes?.let { (start, end) -> s.substring(start, end) }.orEmpty()
    }

    private fun String.isPalindrome(start: Int = 0, end: Int = lastIndex): Boolean {
        var i = start
        var j = end

        while (i <= j) {
            if (this[i] != this[j]) return false

            i++; j--
        }

        return true
    }
}
