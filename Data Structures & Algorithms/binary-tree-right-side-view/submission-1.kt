/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    /**

        You are given the root of a binary tree.
        
        Return only the values of the nodes that are visible from the 
        right side of the tree, ordered from top to bottom.

     */
    fun rightSideView(root: TreeNode?): List<Int> {
        return bfs(root)
    }

    private fun bfs(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<Pair<Int, TreeNode>>()

        queue.addLast(0 to root)

        while (queue.isNotEmpty()) {
            val (level, node) = queue.removeFirst()

            if (result.size == level) {
                result.add(node.`val`)
            } else {
                result[level] = node.`val`
            }

            node.left?.also { queue.add(level + 1 to it) }
            node.right?.also { queue.add(level + 1 to it) }
        }

        return result
    }

    /**

        Edge cases:
            - empty tree, return emptyList()

        Traverse all tree
            starting from right
            if result[level] has no value, assign it

     */
    private fun dfs(root: TreeNode?): List<Int> {
        if (root == null) emptyList<Int>()

        val result = mutableListOf<Int>()

        fun dfs(node: TreeNode?, level: Int = 0) {
            if (node == null) return
            
            if (result.size <= level) result.add(node.`val`)

            dfs(node.right, level + 1)
            dfs(node.left, level + 1)
        }

        dfs(root)

        return result
    }
}
