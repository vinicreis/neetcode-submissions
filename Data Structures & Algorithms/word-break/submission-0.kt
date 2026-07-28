class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        return solution(s, wordDict)
    }

    /**

        Edge cases:
            - wordDict is empty, return false
            - s is empty: return s in wordLength

        [ "need", "code" ]

                 ij
        "neetcode"

     */
    private fun solution(s: String, wordDict: List<String>): Boolean {
        if (wordDict.isEmpty()) return false
        if (s.isEmpty()) return s in wordDict

        var i = 0
        var j = 0

        while (j < s.length) {
            val substring = s.substring(i, j + 1)

            if (substring in wordDict) {
                i = j + 1
                j = i
            } else {
                j++
            }
        }

        return i == j
    }
}
