/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun invertTree(root: TreeNode?): TreeNode? {
        return iteration(root)
    }

    /**

        create a queue with the root
        while queue is not empty
            swap right and left
            add right to queue, if exists
            add left to queue, if exists

     */
    private fun iteration(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val queue = LinkedList<TreeNode>()

        queue.offer(root)

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            val temp = node.left
            node.left = node.right
            node.right = temp

            node.left?.also(queue::offer)
            node.right?.also(queue::offer)
        }

        return root
    }

    /**

        Recusively
        Swap left to right
        call again on left and right
        stop if null
        return the head

     */
    private fun recursion(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val temp = root.left
        root.left = root.right
        root.right = temp

        root?.left?.also(::recursion)
        root?.right?.also(::recursion)

        return root
    }
}
