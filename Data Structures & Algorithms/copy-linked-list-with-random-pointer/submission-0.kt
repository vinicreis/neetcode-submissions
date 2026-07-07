/*
// Definition for a Node.
class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}
*/

class Solution {
    /**

        You are given the head of a linked list of length n.
        Unlike a singly linked list, each node contains an additional pointer random, which may point to any node in the list, or null.

        Create a deep copy of the list.

        The deep copy should consist of exactly n new nodes, each including:

        The original value val of the copied node
        A next pointer to the new node corresponding to the next pointer of the original node
        A random pointer to the new node corresponding to the random pointer of the original node
        Note: None of the pointers in the new list should point to nodes in the original list.

        Return the head of the copied linked list.

     */
    fun copyRandomList(head: Node?): Node? {
        if (head == null) return null

        return solution(head)
    }

    /**

        Store the the copies using the original nodes as keys.
        So then, we can use the `next` and `random` pointers to know which node to point the copies to.
        Storing (null, null) is convenient to setup nodes that points to the end of the list

     */
    private fun solution(head: Node?): Node? {
        // Time: O(n)
        // Space: O(n)
        val stack = hashMapOf<Node?, Node?>(null to null)

        var current: Node? = head

        while (current != null) {
            val copy = Node(current.`val`)
            stack[current] = copy
            current = current.next
        }

        current = head

        while (current != null) {
            val copy = stack[current]
            copy?.next = stack[current.next]
            copy?.random = stack[current.random]
            current = current.next
        }

        return stack[head]
    }
}
