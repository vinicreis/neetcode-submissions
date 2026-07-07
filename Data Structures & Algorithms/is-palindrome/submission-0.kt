class Solution {
    fun isPalindrome(s: String): Boolean {
        return solution1(s)
    }

    /**
        Palindromes are often solved by double pointers.

        Some edge cases:
            - s is empty: false // No mislead
        
        Some considerations:
            - Consider alphanumerics only. How?
                - Ignoring case
                - Filter? Not that optimal
                - On double pointer approach

                 i                          j                
                  i                        j                
                   i                      j                
                    i                    j                
                     i                  j                
                "This is a cat? tac a si sihT"

                - If i hits a non-alphanumeric, i++
                - If j hits a non-alphanumeric, j--
                - Do it separatelly, covering the scenario where non-alpha is assymetric
     */
    private fun solution1(s: String): Boolean {
        var i = 0
        var j = s.lastIndex

        while (i < j) {
            val start = s[i].toString()
            val end = s[j].toString()

            // println("Compare $start at $i to $end at $j")

            if (start.consider().not()) { i++; continue }
            if (end.consider().not()) { j--; continue }

            if (start.equals(end, ignoreCase = true).not()) {
                return false
            }

            i++
            j--
        }

        return true
    }

    private companion object {
        private val AllowedRegex = "[a-zA-Z0-9]".toRegex()

        private fun String.consider(): Boolean = matches(AllowedRegex)
    }
}
