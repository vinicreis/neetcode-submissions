class Solution {
    /**

        You are given two integer arrays nums1 and nums2 of size m and n respectively, 
        where each is sorted in ascending order.
        
        Return the median value among all elements of the two arrays.

        Your solution must run in O(log(m+n)) time.

     */
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        return binarySearchMedian(nums1, nums2)
    }

    private fun binarySearchMedian(nums1: IntArray, nums2: IntArray): Double {
        val size = nums1.size + nums2.size

        val x = findKth(nums1, nums1.size, nums2, nums2.size, (size + 1) / 2)
        val y = findKth(nums1, nums1.size, nums2, nums2.size, (size + 2) / 2)
        val median = (x + y).toDouble() / 2

        println("X: $x / Y: $y\nResult: $median")

        return median
    }

    private fun findKth(a: IntArray, m: Int, b: IntArray, n: Int, k: Int, aIndex: Int = 0, bIndex: Int = 0): Int {
        if (m > n) return findKth(b, n, a, m, k, bIndex, aIndex)

        if (m == 0) return b[bIndex + k - 1]
        if (k == 1) return min(a[aIndex], b[bIndex])

        val i = min(m, k / 2)
        val j = min(n, k / 2)

        println("i = $i\nj = $j\n")

        return if (a[aIndex + i - 1] > b[bIndex + j - 1]) {
            findKth(a, m, b, n - j, k - j, aIndex, bIndex + j)
        } else {
            findKth(a, m - i, b, n, k - i, aIndex + i, bIndex)
        }
    }

    /**

        nums1 = [ 1, 3, 5, 6, 7, 8, 9, 10 ]
        nums2 = [ 2, 4, 6, 7, 8, 9 ]

            [ 1, 2, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9, 10 ]
                            6 + 6 / 2
                              6


        nums1 = [ 1, 3, 5, 6, 7, 8, 9, 10 ]
        nums2 = [ 2, 44, 64, 74, 84, 94 ]

            [ 1, 2, 3, 5, 6, 7, 8, 9, 10, 44, 64, 74, 84, 94 ]
            
        result = 17 / 2 = 8.5

        merge the arrays would be a possible options
        but, it would take O(n + m), which is more complex than O(log(n + m))
        Not an option

        What guides the search is the total size of both arrays
        
        t = n.size + m.size
        mid = t / 2

        What I need to do is find one or two elements closest to position mid
        one element if t % 2 == 1, otherwise 2
     */
    private fun intuition(nums1: IntArray, nums2: IntArray): Double {
        var from1 = 0
        var to1 = nums1.lastIndex
        var from2 = 0
        var to2 = nums2.lastIndex
        var index1 = from1 + (to1 - from1) / 2
        var index2 = from2 + (to2 - from2) / 2

        while (from1 <= to1 && from2 <= to2) {
            val found1 = nums1[index1]
            val found2 = nums2[index2]

            if (found1 < found2) {
                from1 = index1 + 1
                to2 = index2 - 1
            } else {
                to1 = index1 - 1
                from2 = index2 + 1
            }
        }

        val even = (nums1.size + nums2.size) % 2 == 0

        return when {
            nums1[index1] >= nums2[index2] && even -> (nums1[index1] + nums1[index1.inc()]).toDouble() / 2
            nums1[index1] >= nums2[index2] -> nums1[index1].toDouble()
            even -> (nums2[index2] + nums2[index2.inc()]).toDouble() / 2
            else -> nums2[index2].toDouble()
        }
    }
}
