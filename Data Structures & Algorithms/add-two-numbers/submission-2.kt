/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    /**

        You are given two non-empty linked lists, l1 and l2, where each represents a non-negative integer.

        The digits are stored in reverse order, e.g. the number 321 is represented as 1 -> 2 -> 3 -> in the linked list.

        Each of the nodes contains a single digit. You may assume the two numbers do not contain any leading zero, except the number 0 itself.

        Return the sum of the two numbers as a linked list.

     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        return l1 + l2
    }

    private operator fun ListNode?.plus(other: ListNode?): ListNode? {
        return this.plus(other, carry = 0)
    }

    private fun ListNode?.plus(other: ListNode?, carry: Int): ListNode? {
        if (this == null && other == null && carry == 0) return null

        val x = this?.`val` ?: 0
        val y = other?.`val` ?: 0

        val sum = x + y + carry
        val newCarry = sum / 10
        val nodeVal = sum % 10

        val next = this?.next.plus(other?.next, newCarry)
        val node = ListNode(nodeVal)

        node.next = next

        return node
    }

    /**

        Edge cases:
            - l1 or l2 empty = not possible
            
     */
    private fun stringConvertion(l1: ListNode?, l2: ListNode?): ListNode? {
        val x = l1.toNumber()
        val y = l2.toNumber()

        val result = x + y

        // println("Result: $result | Path: "); result.toNumberNode().also { it.printPathLn() }

        return result.toNumberNode()
    }

    /**
        1. Pass a multiplier, starting at 10 ^ 0, multiply by 10 every time and sum results
        or
        2. Use a string to concatenate and then convert back to int

        1. limits if l.size is too big uses l.size * 2 bytes
        2. also, but uses l.size * 1 byte
     */
    private fun ListNode?.toNumber(): Int = toStringNumber()?.toIntOrNull() ?: -1

    private fun ListNode?.toStringNumber(): String {
        if (this == null) return ""
        if (next == null) return `val`.toString()

        return next.toStringNumber() + `val`.toString()
    }

    /**

        Same strategy, convert to string, and go through each character
        converting to int, recursevely, and return backwards either

     */
    private fun Int.toNumberNode(): ListNode? {
        return toString().toNumberNode()
    }

    private fun String.toNumberNode(): ListNode? {
        // println("Number: $this")

        if (length == 0) return null
        if (length == 1) return ListNode(toInt()) 
        
        val head = takeLast(length - 1).toNumberNode()
        val node = ListNode(first().toString().toInt())
        var next = head

        while (next?.next != null) {
            next = next?.next
        }

        next?.next = node

        // head.printPathLn()
        
        return head
    }

    private fun ListNode?.printPathLn() {
        if (this == null) { println("null"); return }

        print(`val`)
        next?.also {
            print(" -> ")
            it.printPathLn()
        } ?: println()
    }
}
