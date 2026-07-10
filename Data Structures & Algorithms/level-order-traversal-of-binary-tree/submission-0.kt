/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    /**

        Given a binary tree root, return the level order traversal of it as a nested list, 
        where each sublist contains the values of nodes at a particular level in the tree,
        from left to right.

     */
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        return solution(root)
    }

    /**

        Edge cases:
            - root == null, empty list

        - starting on root
        - add val to a list
        - return lists result + (left + right) results

     */
    private fun solution(
        root: TreeNode?,
        level: Int = 1,
        levels: TreeMap<Int, List<Int>> = TreeMap(),
    ): List<List<Int>> {
        if (root == null) return emptyList()

        levels[level] = levels.getOrDefault(level, listOf()) + listOf(root.`val`)

        solution(root.left, level + 1, levels)
        solution(root.right, level + 1, levels)

        return levels.values.toList()
    }
}
