/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    /**

        The diameter of a binary tree is defined as the length of the longest path between any two nodes within the tree. 
        
        The path does not necessarily have to pass through the root.

        The length of a path between two nodes in a binary tree is the number of edges between the nodes.
        
        Note that the path can not include the same node twice.

        Given the root of a binary tree root, return the diameter of the tree.

     */
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        dfs(root)

        return dfsResult
    }

    /**

        Edge cases
            - root is null, 0

     */
    private var dfsResult = 0
    private fun dfs(root: TreeNode?): Int {
        if (root == null) return 0

        val right = dfs(root.right)
        val left = dfs(root.left)

        dfsResult = max(dfsResult, right + left)

        return 1 + max(right, left)
    }
}
