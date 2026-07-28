class Solution {
    fun longestPalindrome(s: String): String {
        return doublePointer(s)
    }

    /**

        Edge cases:
            - s.length = 0, return s
            - s.length = 1, return s

          ij
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
