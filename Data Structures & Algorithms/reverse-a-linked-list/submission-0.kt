/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return null
        if (head.next == null) return head

        return solution(head)
    }

    /**
        [ 0, 1, 2, 3 ]

        0 -> 1 -> 2 -> 3

        3 -> 2 ->

        recursively,

        if head is null, return null
        if next is null and previous dont, return new node where next is previous, and value is head
        if next is null, return the head

        new node, 
            val = head.val
            next = 
     */
    private fun solution(head: ListNode?): ListNode? {
        var newHead = head // New head: 1 -> 2 -> 3 -> 4

        if (head?.next != null) {
            newHead = solution(head?.next) // 2, 3, 4

            head?.next?.next = head // 4 -> 3 -> 2
        }
        
        head?.next = null // 3 -> null
        
        return newHead
    }
}
