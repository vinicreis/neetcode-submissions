/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    fun hasCycle(head: ListNode?): Boolean {
        return head.fsCycle()
    }

    private fun ListNode?.fsCycle(): Boolean {
        if (this == null) return false

        var slow = this
        var fast = this

        while (fast != null && fast?.next != null) {
            slow = slow?.next
            fast = fast?.next?.next

            if (slow == fast) return true
        }

        return false
    }
}
