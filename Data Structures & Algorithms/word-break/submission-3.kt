class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        return dp(s, wordDict)
    }

    private fun dp(s: String, wordDict: List<String>): Boolean {
        if (wordDict.isEmpty()) return false
        if (s.isEmpty()) return s in wordDict

        val memo = hashMapOf(s.length to true)

        fun rec(i: Int = 0): Boolean {
            memo[i]?.also { return it }

            for (word in wordDict) {
                if (i + word.length > s.length) continue

                val substring = s.substring(i, i + word.length)

                if (word == substring && rec(i + word.length)) {
                    memo[i] = true

                    return true
                }
            }

            memo[i] = false

            return false
        }

        return rec()
    }

    private fun rec(s: String, wordDict: List<String>): Boolean {
        if (wordDict.isEmpty()) return false
        if (s.isEmpty()) return s in wordDict

        fun rec(i: Int = 0): Boolean {
            if (i >= s.length) return true

            for (word in wordDict) {
                if (i + word.length > s.length) continue

                val substring = s.substring(i, i + word.length)

                if (word == substring && rec(i + word.length)) return true
            }

            return false
        }

        return rec()
    }
}
