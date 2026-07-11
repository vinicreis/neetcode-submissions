/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun isValidBST(root: TreeNode?): Boolean {
        return dfs(root)
    }

    /**

        dfs
        pass 


     */
    private fun dfs(root: TreeNode?): Boolean {
        if (root == null) return true

        fun dfs(node: TreeNode?, left: Int = Int.MIN_VALUE, right: Int = Int.MAX_VALUE): Boolean {
            if (node == null) return true

            if (node.`val` < left || node.`val` > right) return false

            return dfs(node.left, left, right = node.`val`)
                && dfs(node.right, left = node.`val`, right)
        }
        
        return dfs(root)
    }
}
