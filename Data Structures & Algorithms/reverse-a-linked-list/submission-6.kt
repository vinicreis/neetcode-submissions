/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        return head.itReversed()
    }

    private fun ListNode?.itReversed(): ListNode? {
        if (this == null) return null
        if (next == null) return this

        var prev: ListNode? = null
        var head = this

        while (head != null) {
            val temp = head.next
            head.next = prev
            prev = head
            head = temp
        }

        return prev
    }

    private fun ListNode?.recReversed(): ListNode? {
        var head = this

        if (this?.next != null) {
            head = next.recReversed()

            next?.next = this
        }

        this?.next = null

        return head
    }
}
