/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun goodNodes(root: TreeNode?): Int {
        return dfs(root)
    }

    /**

        To know if there is a greater value, we can
            - pass the whole path
            - pass the max value (?)

     */
    private fun dfs(root: TreeNode?): Int {
        if (root == null) return 0

        var result = 0

        fun dfs(node: TreeNode?, maxVal: Int) {
            if (node == null) return

            if (node.`val` >= maxVal) result++

            dfs(node.left, max(maxVal, node.`val`))
            dfs(node.right, max(maxVal, node.`val`))
        }

        dfs(root, root.`val`)

        return result
    }
}
