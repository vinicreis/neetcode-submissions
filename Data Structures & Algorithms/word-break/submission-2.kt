class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        return rec(s, wordDict)
    }

    /**
     */
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
