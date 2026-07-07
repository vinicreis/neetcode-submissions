class Solution {
    /**

        You are given an array of integers nums containing n + 1 integers. 
        
        Each integer in nums is in the range [1, n] inclusive.

        There is exactly one repeated integer in nums, and every other integer appears at most once.

        Return the repeated integer.

     */
    fun findDuplicate(nums: IntArray): Int {
        return intuition(nums)
    }

    /**

        Edge cases:
            - nums empty: return -1
            - not repeated: -1
            - more than 1 repeated? return first repeated


        Traverse the list, saving visited values on a hashset
     */
    private fun intuition(nums: IntArray): Int {
        val visited: HashMap<Int, Int> = HashMap()

        nums.forEach { num -> 
            if (visited.containsKey(num)) return num

            visited[num] = 1
        }

        return -1
    }
}
