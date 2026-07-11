/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun isValidBST(root: TreeNode?): Boolean {
        return bfs(root)
    }

    private fun bfs(root: TreeNode?): Boolean {
        if (root == null) return true

        val queue = LinkedList<Pair<TreeNode, Pair<Int, Int>>>()

        queue.offer(root to (Int.MIN_VALUE to Int.MAX_VALUE))

        while (queue.isNotEmpty()) {
            val (node, range) = queue.poll()
            val (left, right) = range
            val value = node.`val`

            if (value <= left || value >= right) return false

            node.left?.also { queue.offer(it to (left to value)) }
            node.right?.also { queue.offer(it to (value to right)) }
        }

        return true
    }

    private fun dfs(root: TreeNode?): Boolean {
        if (root == null) return true

        fun dfs(node: TreeNode?, left: Int = Int.MIN_VALUE, right: Int = Int.MAX_VALUE): Boolean {
            if (node == null) return true

            if (node.`val` <= left || node.`val` >= right) return false

            return dfs(node.left, left, right = node.`val`)
                && dfs(node.right, left = node.`val`, right)
        }

        return dfs(root)
    }
}
