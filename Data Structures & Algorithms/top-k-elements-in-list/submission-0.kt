class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        return solution1(nums, k)
    }

    /*
        Edge cases: 
            - Empty list = []
            - k < nums.size = return the most frequent available?

            - order counts? Appearently not

        k = 3
        [ 1, 2, 3, 3, 4, 4, 4, 6, 6, 6, 6 ]

        returns [ 3, 4, 6 ]

        k = 3
        [ 1, 2, 2, 3, 3, 3, 4, 4 ]

        returns 2, 3, 4

        Seems like a frequency problem. HashMaps!!!!

        - build a hashMapOf<Int, Int>() // key is number, value is frequency
        - iterate nums
            - map[num] = (map[num] ?: 0) + 1
        - get map entries, sort by frequency (asc/desc) (value)
        - take k (if asc, take last, otherwise, first) elements
    */
    private fun solution1(nums: IntArray, k: Int): IntArray {
        val map = hashMapOf<Int, Int>()

        for (num in nums) {
            map[num] = (map[num] ?: 0) + 1
        }

        return map.entries
            .sortedBy { it.value }
            .map { it.key }
            .takeLast(k)
            .toTypedArray()
            .toIntArray()
    }
}
