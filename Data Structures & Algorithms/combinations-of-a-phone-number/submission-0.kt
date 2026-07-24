class Solution {
    fun letterCombinations(digits: String): List<String> {
        return solution(digits)
    }

    /**

        - Edge cases
            - digits are empty, return empty
            - if digit !in [2 .. 9], unconsider

        [ "", "", "abc", "def", ..., "wxyz" ]
        { "2": "abc", "3": "def", ..., "9": "wxyz" }

        digits = "34" = [ "3", "4" ] -> [ "def", "ghi" ]
        temp = ""

        temp = ""
        temp = "d" -> dI = 0, oI = 0
        temp = "dg"  -> dI = 1, oI = 0
        temp = "dg"  -> dI = 2, oI = 0 -> add to result

        temp = "dg"  -> dI = 1, oI = 1 -> add to result
        temp = "dg"  -> dI = 1, oI = 1 -> add to result

        dfs(digitIndex: Int, optionIndex: Int)
            if digitIndex >= digits.size
                add to result; return

            if (optionIndex > Letters[digits[digitsIndex]].lastIndex)
                return
                
            dfs(di + 1, oi, temp + Letters[digits[digitsIndex]][optionIndex])
            dfs(di + 1, oi + 1, temp + Letters[digits[digitsIndex]][optionIndex])


        [ "dg", "dh", "di", "eg", "eh", "ei", "fg", "fh", "fi" ]

     */
    private fun solution(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()

        val result = mutableListOf<String>()

        fun dfs(i: Int, temp: String) {
            if (temp.length >= digits.length) { result.add(temp); return }

            for (c in Letters[digits[i]]!!) {
                dfs(i + 1, temp + c)
            }
        }

        dfs(0, "")

        return result
    }

    companion object {
        val Letters = hashMapOf(
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tuv",
            '9' to "wxyz",
        )
    }
}
