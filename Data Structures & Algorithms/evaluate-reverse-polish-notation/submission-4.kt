class Solution {
    fun evalRPN(tokens: Array<String>): Int {
        return stack(tokens)
    }

    /**
        [ "1", "2", "+", "3", "*", "4", "-" ]
        [ "4", "13", "5", "/", "+" ]

        stack
            [ "4", "2" ]

        Stack - LIFO:
            val stack = ArrayDeque<Int>()

            for token in tokens
                if operand: stack.addLast(token.toInt())
                if op: 
                    y = stack.removeLast() // 5
                    x = stack.removeLast() // 13

                    result = x op y // 13 / 5 = 2
                    results.addLast(result)

     */
    private fun stack(tokens: Array<String>): Int {
        // Time: O(n)
        // Space: O(n)
        val results = ArrayDeque<Int>() // Space: O(n)

        tokens.forEach { token -> // O(n)
            when (token) {
                "+" -> { 
                    val y = results.removeLast()
                    val x = results.removeLast()

                    results.addLast(x + y)
                }
                "-" -> { 
                    val y = results.removeLast()
                    val x = results.removeLast()

                    results.addLast(x - y)
                }
                "*" -> { 
                    val y = results.removeLast()
                    val x = results.removeLast()

                    results.addLast(x * y)
                }
                "/" -> { 
                    val y = results.removeLast()
                    val x = results.removeLast()

                    results.addLast(x / y)
                }
                else -> results.addLast(token.toInt())
            }
        }

        return results.last()
    }

    /**
        tokens = [ ]
        r = -Inf

        tokens = [ "1", "2", "+", "3", "*", "4", "-" ]
        ((1 + 2) * 3) - 4 = 5

           x    y   op = x op y = r
        [ "1", "2", "+", "3", "*", "4", "-" ]

           x    y   op = x op y = r
        [ "r", "3", "*", "4", "-" ]
            
                ...

        [ ]

        - REQUIRES INLINE INPUT
        Intuition
            Space: O(1)
            Time: O(n)

            if (tokens.size < 3) return Int.MIN_VALUE

            var i = 0
            val result: Int? = null

            while (i < stokens.lastIndex)
                if (result == null) {
                    val (xText, yText, op) = tokens.subList(0, 3)
                    val x = xText.toIntOrNull() ?: error("Operand at 0 (\"${tokens[0]\") is not a number")
                    val y = yText.toIntOrNull() ?: error("Operand at 1 (\"${tokens[1]\") is not a number")

                    result = when (op) {
                        "+" -> x + y
                        "-" -> x - y
                        "*" -> x * y
                        "/" -> x / y
                        else -> error("Operator at 2 is invalid: \"$op\"")
                    }

                    i = 3
                } else {
                    val (yText, op) = tokens.subList(i, i + 2)
                    val y = yText.toIntOrNull() ?: error("Operand at $i (\"${tokens[i]\") is not a number")

                    when (op) {
                        "+" -> result += y
                        "-" -> result -= y
                        "*" -> result *= y
                        "/" -> result /= y
                        else -> error("Operator at ${i + 1} is invalid: \"$op\"")
                    }

                    i += 2
                }

            return result ?: Int.MIN_VALUE
     */
    // private fun intuition(tokens: Array<String>): Int {
    //     if (tokens.size < 3) return Int.MIN_VALUE

    //     var i = 0
    //     var result: Int? = null

    //     while (i < tokens.lastIndex) {
    //         if (result == null) {
    //             val (xText, yText, op) = tokens.toList().subList(0, 3)
    //             val x = xText.toIntOrNull() ?: error("Operand at 0 (\"${tokens[0]}\") is not a number")
    //             val y = yText.toIntOrNull() ?: error("Operand at 1 (\"${tokens[1]}\") is not a number")

    //             result = when (op) {
    //                 "+" -> x + y
    //                 "-" -> x - y
    //                 "*" -> x * y
    //                 "/" -> x / y
    //                 else -> error("Operator at 2 is invalid: \"$op\"")
    //             }

    //             i = 3
    //         } else {
    //             val (yText, op) = tokens.toList().subList(i, i + 2)
    //             val y = yText.toIntOrNull() ?: error("Operand at $i (\"${tokens[i]}\") is not a number")

    //             when (op) {
    //                 "+" -> result += y
    //                 "-" -> result -= y
    //                 "*" -> result *= y
    //                 "/" -> result /= y
    //                 else -> error("Operator at ${i + 1} is invalid: \"$op\"")
    //             }

    //             i += 2
    //         }
    //     }

    //     return result ?: Int.MIN_VALUE
    // }

    private companion object {
        private val Operators = listOf("+", "-", "*", "/")
    }
}
