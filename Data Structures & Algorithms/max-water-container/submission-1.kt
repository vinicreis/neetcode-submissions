class Solution {
    fun maxArea(heights: IntArray): Int {
        // println(twoPointer(intArrayOf(2, 5, 1, 8, 6)))

        return twoPointer(heights)
    }

    /*
        - heights where each element if a height of a container
        - Pick any two heights with the greater area

        - Edge cases:
            - heights size < 2 return -1 or 0
            - negative heights, unconsider
            - zero, works by algorithm, since 0 * x = 0


                     |
                     |
                     |__
                _____|__|
               |_____|__|
               |_____|__|
               |__|__|__|
             __|__|__|__|
            |__|__|__|__|
        - [ 1, 5, 3, 8, 6 ]
        - result = 15

        - Brute force

                 i        j
            [ 1, 5, 3, 8, 6 ]

            maxArea = 0

            iterate i in heights.indices
                iterate j from i + 1 to heights.lastIndex
                    area = min(i, j) * (j - i)
                    maxArea = max(maxArea, area)
    */
    private fun bruteForce(heights: IntArray): Int {
        if (heights.size < 2) return 0

        var maxArea = 0

        for (i in heights.indices) {
            for (j in (i + 1) .. heights.lastIndex) {
                val area = min(heights[i], heights[j]) * (j - i)

                // println("Area with $i:${heights[i]} and $j:${heights[j]} = $area")

                maxArea = max(area, maxArea)
            }
        }

        return maxArea
    }

    /**
        Two pointers
          i           j
        [ 2, 5, 1, 8, 6 ]

          i           j
        [ 2, 5, 1, 8, 6 ]

        Any way to save each interaction?
        res = 10
        [ 2, 5, 1, 8, 6 ]
          -------------
          saved 8
             ----------
             saved 15
     */
    private fun twoPointer(heights: IntArray): Int {
        if (heights.size < 2) return 0

        var i = 0
        var j = heights.lastIndex
        var result = 0

        while (i < j) {
            val area = min(heights[i], heights[j]) * (j - i)

            // println("Area on $i=${heights[i]} to $j=${heights[j]}: $area")

            result = max(area, result)

            if (heights[i] < heights[j]) {
                i++
            } else {
                j--
            }
        }

        return result
    }
}
