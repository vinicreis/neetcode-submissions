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
            for (l in 0 until i) {
                if (s.isPalindrome(l, i)) {
                    val length = i - l

                    if (length > maxLength) {
                        maxLength = length
                        indexes = l to i.inc()
                    }
                }
            }

            for (r in i until s.length) {
                if (s.isPalindrome(i, r)) {
                    val length = r - i

                    if (length > maxLength) {
                        maxLength = length
                        indexes = i to r.inc()
                    }
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
