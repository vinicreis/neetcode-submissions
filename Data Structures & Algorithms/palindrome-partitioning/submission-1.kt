class Solution {
    fun partition(s: String): List<List<String>> {
        return solution(s)
    }

    /**

        Backtracking

        Question is, where to decide to put or not

        for maxsize in 1 until s.size
            rec(i = 0, maxSize = maxsize, current = "", words = mutableListOf())

        rec(i: Int = 0, maxSize: Int, current: String, words: MutableList<String>)
            if (i >= s.lastIndex) return
            if (current.size > maxSize) return

            if (current.isPalindrome())
                words.add(current)

            rec(i + 1, current, words)
            rec(i + 1, current + s[i], words)
     */
    private fun solution(s: String): List<List<String>> {
        val result = mutableListOf<List<String>>()
        val words = mutableListOf<String>()

        fun dfs(i: Int, j: Int) {
            // println("Words on i $i and j $j: $words")

            if (j >= s.length) {
                if (i == j) result.add(words.toList())

                // println("Result on i $i and j $j: $result")

                return
            }

            val substring = s.substring(i, j + 1)

            if (substring.isPalindrome()) {
                words.add(substring)
                dfs(j + 1, j + 1)
                words.removeLast()
            }

            dfs(i, j + 1)
        }

        dfs(0, 0)

        return result
    }

    private fun String.isPalindrome(): Boolean {
        if (isEmpty()) return false

        var i = 0
        var j = lastIndex

        while (i <= j) {
            if (this[i] != this[j]) return false

            i++; j--
        }

        return true
    }
}
