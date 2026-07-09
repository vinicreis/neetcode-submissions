/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun invertTree(root: TreeNode?): TreeNode? {
        return solution(root)
    }

    /**

        Recusively
        Swap left to right
        call again on left and right
        stop if null
        return the head

     */
    private fun solution(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val temp = root.left
        root.left = root.right
        root.right = temp

        root?.left?.also(::solution)
        root?.right?.also(::solution)

        return root
    }
}
