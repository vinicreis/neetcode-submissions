/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        return dfs(root, k)
    }

    /**

        Search the whole tree, with a sorted list of elements

    */
    private fun dfs(root: TreeNode?, k: Int): Int {
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
