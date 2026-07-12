class Solution {
    fun generateParenthesis(n: Int): List<String> {
        return dfs(n)
    }

    private fun dfs(n: Int): List<String> {
        if (n <= 0) return emptyList()

        val result = mutableListOf<String>()

        fun dfs(parentheses: String = "") {
            if (parentheses.length == n * 2) { 
                if (parentheses.validParentheses()) result.add(parentheses)
                return 
            }
            
            dfs(parentheses + "(")
            dfs(parentheses + ")")
        }

        dfs()

        return result
    }

    private fun String.validParentheses(): Boolean {
        var open = 0

        for (c in this) {
            if (c == '(') open++
            if (c == ')') open--
            if (open < 0) return false
        }

        return open == 0
    }
}
