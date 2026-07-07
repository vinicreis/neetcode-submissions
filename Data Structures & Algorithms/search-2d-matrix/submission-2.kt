class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        return binarySearch(matrix, target)
    }

    /**
        Scan all items looking for the element
     */
    private fun bruteForce(matrix: Array<IntArray>, target: Int): Boolean {
        // Time:  O(n²)
        // Space: O(1)
        if (matrix.isEmpty()) return false

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == target) return true
            }
        }

        return false
    }

    /**
        Consider that each row is sorted, and next row only contains elements greater than last row.

        Check if last element is greater then target
            if is, go to next row
            if not, binary search row
     */
    private fun binarySearch(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false

        var i = 0

        while (i <= matrix.lastIndex) {
            if (matrix[i].isEmpty()) { i++; continue }
            if (matrix[i].last() < target) { i++; continue }

            return matrix[i].binarySearch(target)
        }

        return false
    }

    private fun IntArray.binarySearch(target: Int): Boolean {
        if (isEmpty()) return false

        var from = 0
        var to = lastIndex

        while (from <= to) {
            val index = from + (to - from) / 2
            val found = this[index]

            when {
                found > target -> to = index - 1
                found < target -> from = index + 1
                else -> return true
            }
        }

        return false
    }
}
