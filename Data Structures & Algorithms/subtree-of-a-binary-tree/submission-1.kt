/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        return solution(root, subRoot)
    }

    /**

        Edge cases:
            - root null, subRoot null, then return true
            - otherwise, if any null, return false

        Recursion
            - if root's val == subroot's val, return their compare result
            - otherwise, call(root.right, subRoot) || call(root.left, subRoot)
     */
    private fun solution(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null && subRoot == null) return true
        if (root == null) return false
        if (subRoot == null) return false

        if (root.`val` == subRoot.`val`) {
            return root isEqualTo subRoot || solution(root.left, subRoot) || solution(root.right, subRoot)
        }

        return solution(root.left, subRoot) || solution(root.right, subRoot)
    }

    private infix fun TreeNode?.isEqualTo(other: TreeNode?): Boolean {
        val queue = LinkedList<Pair<TreeNode?, TreeNode?>>()

        queue.offer(this to other)

        while (queue.isNotEmpty()) {
            val (a, b) = queue.poll()

            if (a == null && b == null) continue
            if (a == null) return false
            if (b == null) return false

            if (a.`val` != b.`val`) return false

            queue.offer(a.left to b.left)
            queue.offer(a.right to b.right)
        }

        return true
    }
}
