/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        return list1 + list2
    }

    private operator fun ListNode?.plus(other: ListNode?): ListNode? {
        if (this == null && other == null) return null
        if (this == null) return other
        if (other == null) return this

        return if (this.`val` > other.`val`) {
            other + this
        } else {
            val temp = this.next

            this.next = temp + other

            this
        }
    }
}
