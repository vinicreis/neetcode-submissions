class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        return solution1(nums, target)
    }

    /**
        Context:
            - Can it have repeated numbers? Yes
            - Can it be empty? Yes
            - Negative numbers or zero? Assume yes
            - Any `null`? Assume yes
            - Is it sorted? Assume not

            nums = [ -3, 5, 9, 1, 3, 0, null ]

        1.
              i   j     ...       j 
                  i  j   ...      j 
            [ -3, 5, 9, 1, 3, 0, null ]

            Time complexity: worst O(n^2)
            Space: O(1)

        2. 
              i                   j  
                  i               j  
                  i           j  
                     i        j  
                        i  j  
            [ -3, 5, 9, 1, 3, 0, null ]

              i               j  
                  i           j  
                  i        j  
                     i     j  
                        i  j  
            [ -3, 5, 9, 1, 3, 0 ]

              i               j

              if sum < target: increment i
                  i           j

              if sum > target: decrement j
               i            j


            [ -3 , 0, 1, 2, 5, 9 ]
            
            But, when I sort the numbers, I need something to track original index
            Could be an array with size nums.max() - nums.min()                     | O(n) to access
            Or, a hashmap with where the keys are the numbers and values are index  | O(1) to access :c
    */
    private fun solution1(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf()

        val indexes = nums.originalIndexes()
        val sorted = nums.toIndexedSorted()
        var result = IntArray(size = 2) { -1 }
        var i = 0
        var j = nums.lastIndex

        println("Numbers: ${nums.toList()}")
        println("Indexes: $indexes")

        while (i != j) {
            val sum = sorted[i].value + sorted[j].value

            when {
                sum < target -> i++
                sum > target -> j--
                else -> {
                    result[0] = sorted[i].index
                    result[1] = sorted[j].index

                    break
                }
            }
        }

        return result.sorted().toIntArray()
    }

    private val Pair<Int, Int>.index: Int get() = first
    private val Pair<Int, Int>.value: Int get() = second

    private fun IntArray.toIndexedSorted(): List<Pair<Int, Int>> {
        return mapIndexed { index, number -> index to number }.sortedBy { it.value }
    }

    private fun IntArray.originalIndexes(): HashMap<Int, Int> {
        val indexes = hashMapOf<Int, Int>()

        forEachIndexed { i, number -> indexes[number] = i }

        return indexes
    }
}
