class Solution {
    fun trap(height: IntArray): Int {
        return bruteForce(height)
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

        if (height.isEmpty()) return result

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
            start = null

            if (j == height.lastIndex) break

            given that start == null && height[i] < height[j]
                i++; j++; continue

            given that start == null && height[i] > height[j]
                start = i
                j++

            given that start != null && height[start] > height[j]
                j++

            given that start != null && height[start] <= height[j]
                start = null
                i = j
                j = i + 1

                iterate l from i + 1 to j - 1
                    trapped += min(height[i], height[j]) - height[l]

          i  j
             i  j
             i     j
             i  l  j
                   i  j
                   i     j
                   i        j
                   i  l        j
                   i     l     j
                   i        l  j
                               i  j
        [ 0, 2, 0, 3, 1, 0, 1, 3, 2 ]
     */
    private fun twoPointer(height: IntArray): Int {
        var result = 0

        if (height.size < 3) return result

        var i = 0
        var j = i + 1
        var start: Int? = null

        while (j < height.lastIndex) {
            println("Start on $i and $j: $start")

            when {
                start == null && height[i] < height[j] -> {
                    i++
                    j++
                }

                start == null && height[i] > height[j] -> {
                    start = i
                    j++
                }

                start != null && height[start] > height[j] -> {
                    j++
                }

                start != null && height[start] <= height[j] -> {
                    start = null

                    println("Trapped between $i and $j")

                    for (l in (i + 1) until (j - 1)) {
                        val trapped = min(height[i], height[j]) - height[l]
                        result += trapped

                        println("Trapped on between $i and $j on $l: $trapped | Total: $result")
                    }

                    i = j
                    j = i + 1
                }
            }
        }

        return result
    }
}
