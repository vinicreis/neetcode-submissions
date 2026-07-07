class Solution {
    /**
        We can use a hash map to save the occurrences of each number effiently.

        Brute force approach: O(n)
        
        [1, 2, 3, 4, 5]

        1: { }
        2: { 1: 1 }
        3: { 1: 2, 2: 1 }
        4: { ... }

        [1, 2, 3, 4, 5]
        1: { }
        2: { 1: 1 }
        2: { 1: 2 } -> return true

        Edge cases:
        - Empty list: false :check:
        - `null` values should be considered? Let's do it then
    */
    fun hasDuplicate(nums: IntArray): Boolean {
        if (nums.isEmpty()) return false

        val occurrences = hashMapOf<Int, Int>() // Space: O(n)

        nums.forEach { num -> // Complexity: O(n)
            if (occurrences.containsKey(num)) return true // Access: O(1)

            occurrences[num] = 1
        }
        
        return false
    }
}
