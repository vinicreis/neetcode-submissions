/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    /**

        Given the head of a linked list and an integer n, 
        remove the nth node from the end of the list and return its head.

     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null

        return solution(head, n)
    }

    /**

        1 -> 2 -> 3 -> 4 -> 5
        n = 1

        2 -> 3 -> 4 -> 5
        return node 2

        Edge cases: 
            - head == null, return null
            - n > size? return null, for now

        traverse list, passing n - 1
            1 -> 2 -> 3 -> 4 -> 5
     */
    private fun solution(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null

        val size = head.size()
        val m = size - n

        val newHead = head.removeAt(m)
        
        return if (m > 0) head else newHead
    }

    private fun ListNode?.removeAt(n: Int): ListNode? {
        return when {
            this == null -> null
            n > 1 -> next.removeAt(n - 1)
            n == 0 -> {
                val temp = next
                next = null
                
                temp
            }

            else -> {
                val temp = next
                next = next?.next
                temp?.next = null

                this
            }
        }
    }

    private fun ListNode?.size(): Int {
        if (this == null) return 0
        if (next == null) return 1

        return 1 + next.size()
    }
}
