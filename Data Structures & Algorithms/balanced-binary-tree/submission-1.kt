/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    /**

        Given a binary tree, return true if it is height-balanced and false otherwise.

        A height-balanced binary tree is defined as a binary tree in which the left and right
        subtrees of every node differ in height by no more than 1.

     */
    fun isBalanced(root: TreeNode?): Boolean {
        return iterative(root)
    }

    /**

        create a queue, with nodes
        
        add root to queue

        while queue is not empty
            right height
            left height

            if (abs(right - left) > 1) return false

            add right queue
            add left queue

        return true
     */
    private fun iterative(root: TreeNode?): Boolean {
        if (root == null) return true

        val queue = LinkedList<TreeNode>()

        queue.offer(root)

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            val right = node.right.height()
            val left = node.left.height()

            if (abs(right - left) > 1) return false

            node.right?.also(queue::offer)
            node.left?.also(queue::offer)
        }

        return true
    }

    /**

        Edge cases:
            root == null, return true

        - Recursion


     */
    private fun recursive(root: TreeNode?): Boolean {
        if (root == null) return true

        val right = root.right.height()
        val left = root.left.height()

        if (abs(right - left) > 1) return false

        return recursive(root.right) && recursive(root.left)
    }

    private fun TreeNode?.height(): Int {
        if (this == null) return 0

        return 1 + max(right.height(), left.height())
    }
}
