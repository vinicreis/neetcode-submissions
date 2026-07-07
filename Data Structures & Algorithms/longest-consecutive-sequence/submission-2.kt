class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        return solution1(nums)
    }
    
    /**
        Examples:
            - [ ]                       = 0
            - [ 1, 5, 20, 3, 3, 4 ]     = 2
            - [ 1, 2, 20, 3, 3, 4 ]     = 4
            - [ 1, 2, 3, 4, 10 ]        = 4

        Edge cases:
            - Empty list
            - Repeated numbers are consecutives? No
        
        - Hashmap:
            O(n)
              i
            [ 1, 2, 20, 3, 3, 4 ]
            { 1: 1 }

            
                 i
            [ 1, 2, 20, 3, 3, 4 ]
            { 1: 2, 2: 1 } 
            { 2: 2 } 

            { 1: 2, 2: 1, 20: 1 } 
            { 2: 2, 20: 1 } 

            { 1: 3, 2: 2, 20: 1, 3: 1 } 
            { 3: 3, 20: 1 } 
            { 20: 1, 4: 4 } 
     */
    private fun solution1(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        val sequences = hashMapOf<Int, Int>()

        for (num in nums.sorted()) {
            sequences.incrementOrAdd(num)
        }

        return sequences.maxOf { it.value }
    }

    private fun HashMap<Int, Int>.incrementOrAdd(num: Int) {
        if (containsKey(num-1)) {
            this[num] = this[num-1]!! + 1
            remove(num - 1)
        } else if (containsKey(num).not()) {
            this[num] = 1
        }
    }
}
