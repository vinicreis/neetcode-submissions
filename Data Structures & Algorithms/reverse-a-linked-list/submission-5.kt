/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        return head.recReversed()
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
