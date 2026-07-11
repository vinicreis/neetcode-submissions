/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        return inOrderDfs(root, k)
    }

    /**

        Do a dfs in order, adding the values to a list
        Get the Kth element on the list

    */
    private fun inOrderDfs(root: TreeNode?, k: Int): Int {
        if (root == null) return -1

        val elements = mutableListOf<Int>()

        fun dfs(node: TreeNode?) {
            if (node == null) return

            dfs(node.left)
            elements.add(node.`val`)
            dfs(node.right)
        }

        dfs(root)

        return elements.getOrNull(k - 1) ?: -1
    }
}
