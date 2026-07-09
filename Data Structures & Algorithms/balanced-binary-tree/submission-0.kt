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
        return solution(root)
    }

    /**

        Edge cases:
            root == null, return true

        - Recursion


     */
    private fun solution(root: TreeNode?): Boolean {
        if (root == null) return true

        val right = root.right.height()
        val left = root.left.height()

        if (abs(right - left) > 1) return false

        return solution(root.right) && solution(root.left)
    }

    private fun TreeNode?.height(): Int {
        if (this == null) return 0

        return 1 + max(right.height(), left.height())
    }
}
