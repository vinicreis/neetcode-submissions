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
                var j = i

                while (j < s.length && j - i < word.length) {
                    if (s[j] != word[j - i]) break

                    j++
                }

                if (j - i == word.length && rec(i + j)) return true 
            }

            return false
        }

        return rec()
    }
}
