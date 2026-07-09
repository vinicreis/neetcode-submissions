/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return iterative(p, q)
    }

    private fun iterative(p: TreeNode?, q: TreeNode?): Boolean {
        val queue = LinkedList<Pair<TreeNode?, TreeNode?>>()

        queue.offer(p to q)

        while (queue.isNotEmpty()) {
            val (node1, node2) = queue.poll()

            if (node1 == null && node2 == null) continue
            if (node1 == null) return false
            if (node2 == null) return false
            if (node1.`val` != node2.`val`) return false

            queue.offer(node1.left to node2.left)
            queue.offer(node1.right to node2.right)
        }

        return true
    }

    /**

        Edge cases:
            - If both roots == null, return true
            - If any null, return false

        Recursive
            - if p.`val` != q.`val` return false
            - return call(p.right, q.right) && call(p.left, q.left)
     */
    private fun recursive(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null) return false
        if (q == null) return false

        if (p.`val` != q.`val`) return false

        return recursive(p.left, q.left) && recursive(p.right, q.right)
    }
}
