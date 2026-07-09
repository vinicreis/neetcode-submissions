/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun maxDepth(root: TreeNode?): Int {
        return recursion(root)
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
