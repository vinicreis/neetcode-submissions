class Solution {
    fun longestPalindrome(s: String): String {
        return solution(s)
    }

    private fun solution(s: String): String {
        if (s.isEmpty()) return ""

        val n = s.length
        val dp = Array(n) { BooleanArray(n) }
        var resultIndex = 0
        var resultLength = 0

        for (i in s.lastIndex downTo 0) {
            for (j in i until n) {
                if (s[i] == s[j] && (j - i <= 2 || dp[i+1][j-1])) {
                    dp[i][j] = true

                    if (j - i + 1 > resultLength) {
                        resultLength = j - i + 1
                        resultIndex = i
                    }
                }
            }
        }

        return s.substring(resultIndex, resultIndex + resultLength)
    }
}
