/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    /**

        You are given an array of k linked lists lists, where each list is sorted in ascending order.

        Return the sorted linked list that is the result of merging all of the individual linked lists.

     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        return divideAndConquer(lists)
    }

    private fun divideAndConquer(lists: Array<ListNode?>, start: Int = 0, end: Int = lists.lastIndex): ListNode? {
        // Time: O(n log k)
        // Space: O(log k)
        if (start > end) return null
        if (start == end) return lists[start]

        val i = start + (end - start) / 2
        val list1 = divideAndConquer(lists, start, i) // O(n log k)
        val list2 = divideAndConquer(lists, i + 1, end) // O(n log k)

        return list1 + list2 // O(n + m)
    }

    private operator fun ListNode?.plus(other: ListNode?): ListNode? {
        // Time: O(n + m)
        // Space: O(1)
        if (this == null && other == null) return null
        if (this == null) return other
        if (other == null) return this

        val (head, next) = if (this.`val` < other.`val`) this to other else other to this

        head.next = head.next + next

        return head
    }

    private fun ListNode?.printPath() {
        print(this?.`val`)
        this?.next?.also {
            print(" -> ")
            it.printPath()
        } ?: println()
    }
}
