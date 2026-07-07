/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    /**

        You are given the head of a singly linked-list.

        The positions of a linked list of length = 7 for example, can intially be represented as:

        [0, 1, 2, 3, 4, 5, 6]

        Reorder the nodes of the linked list to be in the following order:

        [0, 6, 1, 5, 2, 4, 3]

        Notice that in the general case for a list of length = n the nodes are reordered to be in the following order:

        [0, n-1, 1, n-2, 2, n-3, ...]

        You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.

     */
    fun reorderList(head: ListNode?): Unit {
        return bruteForce(head)
    }

    /**
        // Space: O(n)
        create a stack, lifo, for the n - i values
        create a queue, fifo, for the i values

        // Time: O(n)
        iterate the list
            switch between add to stack and queue

        // Time: O(n)
        switch between pop from stack and queue, setting the next pointers
     */
    private fun bruteForce(head: ListNode?) {
        if (head == null) return
        if (head.next == null) return

        var current: ListNode? = head
        val values: MutableList<ListNode> = mutableListOf()

        while (current != null) {
            values.add(current)
            
            current = current.next
        }

        var i = 0
        var j = values.lastIndex

        while (i < j) {
            values[i].next = values[j]
            
            if (++i >= j) break

            values[j--].next = values[i]
        }

        values[i].next = null
    }

    private fun Collection<ListNode>.toLog(): String = joinToString { it?.`val`?.toString().orEmpty() }
}
