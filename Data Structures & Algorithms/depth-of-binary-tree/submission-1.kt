/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun maxDepth(root: TreeNode?): Int {
        return iteration(root)
    }

    /**

        result = 1
        Add root, height pairs to a queue

        while queue is not empty
            val (node, height) = queue.poll()

            result = max(result, height)

            if left, add (left, height + 1) to queue
            if right, add (left, height + 1) to queue

        return result

     */
    private fun iteration(root: TreeNode?): Int {
        if (root == null) return 0

        var result = 0
        val queue = LinkedList<Pair<TreeNode, Int>>()

        queue.offer(root to 1)

        while (queue.isNotEmpty()) {
            val (node, height) = queue.poll()

            result = max(result, height)

            node.left?.also { queue.offer(it to height + 1) }
            node.right?.also { queue.offer(it to height + 1) }
        }

        return result
    }

    /**

        Edge cases:
            - if null, return 0

        - recursively
            - 1 (current node) + max(call(right), call(left))

     */
    private fun recursion(root: TreeNode?): Int {
        if (root == null) return 0

        return 1 + max(recursion(root.right), recursion(root.left))
    }
}
