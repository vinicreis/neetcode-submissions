class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        // println(solution1(intArrayOf(-1, -1), -2).toList())

        return solution1(numbers, target)
    }

    /**
        Examples:
            - [ ] or [ any() ]
            - target = any()
            - result = [ ]

            - [ -4, -3, -2, -1, 0, 1, 2, 3, 4 ]
            - target = 7
            - result = [ 7, 8 ]

            - [ 0, 2, 4, 5 ]
            - target = 6
            - result = [ 1, 3 ]

            - [ -5, -4, -2, 0, 9 ]
            - target = 5
            - result = [ 1, 4 ]

        Edge cases: 
            - numbers size < 2 = []

        Seems like a good double pointer problem
        If is sorted in ascending order, it helps the search with 2-p
        Ascending order can guide with pointer to move

        Considerations:
            - Do not use the same element
     */
    private fun solution1(numbers: IntArray, target: Int): IntArray {
        val result = IntArray(2)

        if (numbers.size < 2) return result

        var i = 0
        var j = numbers.lastIndex

        while (i < j) {
            val sum = numbers[i] + numbers[j]

            when {
                sum < target -> i++
                sum > target -> j--
                else -> {
                    result[0] = i + 1
                    result[1] = j + 1

                    break
                }
            }
        }

        return result
    }
}
