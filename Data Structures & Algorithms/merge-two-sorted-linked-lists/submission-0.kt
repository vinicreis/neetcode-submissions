/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    /**

        You are given the heads of two sorted linked lists list1 and list2.

        Merge the two lists into one sorted linked list and return the head of the new sorted linked list.

        The new list should be made up of nodes from list1 and list2.

     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        return solution(list1, list2)
    }

    /**

        list1 = [ 1, 3, 5, 7, 9]
        list2 = [ 2, 4, 6, 8 ]

        result = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ]


        if (list1 == null && list2 == null) return null
        if (list1 == null) return list2
        if (list2 == null) return list1

        val (head, other) = if (list1.`val` < list2.`val`) list1 to list2 else list2 to list1

        head.next = solution(head.next, other)
        
        return head
     */
    private fun solution(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null && list2 == null) return null
        if (list1 == null) return list2
        if (list2 == null) return list1

        val (head, other) = if (list1.`val` < list2.`val`) list1 to list2 else list2 to list1

        head.next = solution(head.next, other)
        
        return head
    }
}
