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
        return iteration(list1, list2)
    }

    /**

     */
    private fun iteration(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null && list2 == null) return null
        if (list1 == null) return list2
        if (list2 == null) return list1

        val dummy = ListNode(0)
        var result: ListNode? = dummy

        var other1 = list1
        var other2 = list2

        while (other1 != null && other2 != null) {
            // println("Other 1: ${other1?.`val`}\nOther 2: ${other2.`val`}")
            // result.print()

            if (other1.`val` < other2.`val`) {
                result?.next = other1
                other1 = other1.next
            } else {
                result?.next = other2
                other2 = other2.next
            }

            result = result?.next
        }

        result?.next = other1 ?: other2

        return dummy.next
    }

    private fun ListNode?.print() {
        print(this?.`val`)

        this?.next?.also {
            print(" -> ")
            it.print()
        } ?: run { println() }
    }

    /**

        list1 = [ 1, 3, 5, 7, 9]
        list2 = [ 2, 4, 6, 8 ]

        result = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
     */
    private fun recursion(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null && list2 == null) return null
        if (list1 == null) return list2
        if (list2 == null) return list1

        val (head, other) = if (list1.`val` < list2.`val`) list1 to list2 else list2 to list1

        head.next = recursion(head.next, other)
        
        return head
    }
}
