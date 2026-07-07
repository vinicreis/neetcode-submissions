class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
        return bruteForce(heights)
    }

    /**
        - Empty heights? return 0
        - Negatives? Shouldn't have
        - Are the heights centered?
            - Centered below

                       |
                    |  |
                 |  |  |
                 |  |  |  |
            __ __|__|__|__|__
    
        heights = [ 0, 3, 4, 5, 2 ]

        [ 0, 3, 4, 5, 2 ]

             0, 0, 0, 0
                3, 6, 6
                   4, 4
                      2

          l ... i   ...  r
          l  i
        [ 7, 1, 7, 2, 2, 4]
             

        Intuition: brute force
            var maxArea = 0

            for (i in 0 until heights.size) {
                val minRightBarHeight = heights[i]
                for (r in i + 1 until heights.size) {
                    minRightBarHeight = min(minRightBarHeight, heights[r])
                }

                val rightMaxArea = minRightBarHeight * (heights.size - i)

                val minLeftBarHeight = heights[i]
                for (l in i - 1 downTo 0) {
                    minLeftBarHeight = min(minLeftBarHeight, heights[l])
                }
                val leftMaxArea = minLeftBarHeight * (i + 1)

                maxArea = max(leftMaxArea, rightMaxArea)
            }

            return maxArea
     */
    private fun bruteForce(heights: IntArray): Int {
        var maxArea = 0

        if (heights.isEmpty()) return maxArea
        // Unconsider negatives for now

        for (i in 0 until heights.size) {
            var rightMaxIndex = i + 1

            while (rightMaxIndex <= heights.lastIndex && heights[rightMaxIndex] >= heights[i]) {
                rightMaxIndex++
            }

            var leftMaxIndex = i
            while (leftMaxIndex >= 0 && heights[leftMaxIndex] >= heights[i]) {
                leftMaxIndex--
            }

            rightMaxIndex--
            leftMaxIndex++

            maxArea = max(maxArea, heights[i] * (rightMaxIndex - leftMaxIndex + 1))
        }

        return maxArea
    }
}
