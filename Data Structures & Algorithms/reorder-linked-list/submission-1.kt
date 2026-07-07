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
        return splitReverseAndMerge(head)
    }

    private fun splitReverseAndMerge(head: ListNode?) {
        if (head == null) return
        if (head.next == null) return

        val middle = head.middle()
        val reversedMiddle = middle.reversed()

        // print("Middle: "); middle.printAll()
        // // print("Reversed middle: "); reversedMiddle.printAll()
        // // print("Head: "); head.printAll()

        head mergeSwappingWith reversedMiddle

        // // print("Result: "); head.printAll()
    }

    private fun ListNode?.middle(): ListNode? {
        if (this == null) return null
        if (next == null) return this

        var previous: ListNode? = null
        var slow: ListNode? = this
        var fast: ListNode? = next

        while (fast != null) {
            previous = slow
            slow = slow?.next
            fast = fast?.next?.next
        }

        previous?.next = null

        return slow
    }

    private fun ListNode?.reversed(): ListNode? {
        if (this == null) return null
        if (next == null) return this

        var current: ListNode? = this
        var next: ListNode? = next

        current?.next = null

        while (next != null) {
            val temp = next?.next
            next?.next = current
            current = next
            next = temp
        }

        return current
    }

    private infix fun ListNode?.mergeSwappingWith(reversed: ListNode?) {
        if (this == null) return
        if (reversed == null) return

        val temp = this.next
        this.next = reversed

        reversed mergeSwappingWith temp
    }

    private fun ListNode?.printAll() {
        this ?: return

        print(`val`)
        
        next?.also {
            print(" -> ")
            it.printAll()
        } ?: println()
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
        // Time: O(n)
        // Space: O(n)
        if (head == null) return
        if (head.next == null) return

        var current: ListNode? = head
        val values: MutableList<ListNode> = mutableListOf()

        while (current != null) { // O(n)
            values.add(current)
            
            current = current.next
        }

        var i = 0
        var j = values.lastIndex

        while (i < j) { // O(n)
            values[i].next = values[j]
            
            if (++i >= j) break

            values[j--].next = values[i]
        }

        values[i].next = null
    }

    private fun Collection<ListNode>.toLog(): String = joinToString { it?.`val`?.toString().orEmpty() }
}
