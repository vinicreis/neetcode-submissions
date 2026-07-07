class Solution {
    fun search(nums: IntArray, target: Int): Int {
        return solution(nums, target)
    }
    
    /**

        - The main problems is to decide which side to refine search

        - which size sorted
        - this side might contain searched element

        - 
        - if left is sorted 
            - target is greater then found, go right
            - target is lower than start, go right
            - otherwise, go left
        - if right is sorted
            - targer is lower then found, go left
            - target is greater than end, go left
            - otherwise, go right

     */
    private fun solution(nums: IntArray, target: Int): Int {
        var result = -1

        if (nums.isEmpty()) return result

        var from = 0
        var to = nums.lastIndex

        while (from <= to) {
            val index = from + (to - from) / 2
            val found = nums[index]

            // println("Searching from $from to $to...")
            // println("Found $found on index $index")

            if (found == target) return index

            when {
                nums[from] <= found && target > found -> from = index + 1
                nums[from] <= found && target < nums[from] -> from = index + 1
                nums[from] <= found -> to = index - 1
                target < found -> to = index - 1
                target > nums[to] -> to = index - 1
                else -> from = index + 1
            }
        }

        return result
    }
}
