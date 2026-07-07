class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        return bruteForce(matrix, target)
    }

    /*
        Scan all items looking for the element
     */
    private fun bruteForce(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == target) return true
            }
        }

        return false
    }
}
