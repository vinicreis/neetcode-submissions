/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    /**

        Given the beginning of a linked list head, return true if there is a cycle in the linked list. Otherwise, return false.

        There is a cycle in a linked list if at least one node in the list can be visited again by following the next pointer.

        Internally, index determines the index of the beginning of the cycle, if it exists.
        
        The tail node of the list will set it's next pointer to the index-th node. 
        
        If index = -1, then the tail node points to null and no cycle exists.

        Note: index is not given to you as a parameter.

     */
    fun hasCycle(head: ListNode?): Boolean {
        return bruteForce(head)
    }

    /**

        1 -> 2 -> 3 -> 4 -> 5 -|
                 /\            | 
                  |____________|

        The cycle is defined by the `val` property or by the reference ifself?

        Example:
            1 -> 2 -> 3 -> 4 -> 5 -> 1

            where 1-nodes are ListNode(1)@abcd1234 and ListNode(1)@wxyz9876 (different instances)

            defines a cycle?

        Assume not for now.

        So, naive approach would be traverse the list saving visited nodes. If a node is in visited, cycle!
     */
    private fun bruteForce(head: ListNode?, visited: List<ListNode> = emptyList()): Boolean {
        if (head == null) return false
        
        if (head in visited) return true

        return bruteForce(head.next, visited + head)
    }
}
