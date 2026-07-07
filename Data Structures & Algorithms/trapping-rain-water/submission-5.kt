class Solution {
    fun trap(height: IntArray): Int {
        return twoPointer(height)
    }

    /*
                            ____
                            |  |
                            |  |
                          __|  |
                         |  |  |
                    __ __|  |  |
                   |  |XX|  |  |__ __
                   |  |XX|  |  |  |XX|
        
        height = [ 2, 0, 4, 7, 0, 1 ]

        Brute force

          i  l     ...      l
          r  i  l    ...    l
          r ... r  i  l ... l
          r      ...     r  i
        [ 5, 0, 1, 4, 7, 0, 1 ]

        var start: Int? = null
        var result = 0

        iterate i in height.indices
            iterate l in 0 until i
                leftMax = maxOf(leftMax, height[l])

            iterate r in i+1 .. height.lastIndex
                rightMax = maxOf(rightMax, height[r])

            result += min(leftMax, rightMax) - height[i]
     */
    fun bruteForce(height: IntArray): Int {
        var result = 0

        if (height.size < 3) return result

        for (i in height.indices) {
            var leftMax: Int = height[i]
            var rightMax: Int = height[i]

            for (l in 0 until i) {
                leftMax = maxOf(leftMax, height[l])
            }
            
            for (r in i+1 .. height.lastIndex) {
                rightMax = maxOf(rightMax, height[r])
            }

            result += min(leftMax, rightMax) - height[i]
        }

        return result
    }

    /**
        Two pointer
            Left max:
            [ 0, 0, 2, 2, 3, 3, 3, 3, 3 ]

            Right max:
            [ 3, 3, 3, 3, 3, 3, 3, 2, 2 ]

            [ 0, 2, 0, 3, 1, 0, 1, 3, 2 ]
     */
    private fun twoPointer(height: IntArray): Int {
        var result = 0

        if (height.size < 3) return result
        
        val leftMax = IntArray(size = height.size).apply {
            this[0] = height[0]
        }
        
        val rightMax = IntArray(size = height.size).apply {
            this[height.lastIndex] = height[height.lastIndex]
        }

        for (l in 1 .. height.lastIndex) {
            leftMax[l] = max(leftMax[l - 1], height[l])
        }

        for (r in height.lastIndex-1 downTo 0) {
            rightMax[r] = max(rightMax[r + 1], height[r])
        }

        for (i in height.indices) {
            result += min(leftMax[i], rightMax[i]) - height[i]
        }

        return result
    }
}
