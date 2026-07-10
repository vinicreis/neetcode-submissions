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
        return bfs(root)
    }

    /**

        Let's try to make the same but with a queue
        where the levels are maintained 

     */
    private fun bfs(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<MutableList<Int>>()
        val queue = LinkedList<Pair<Int, TreeNode>>()
        
        queue.offer(0 to root)

        while (queue.isNotEmpty()) {
            val (level, node) = queue.poll()

            if (result.size == level) {
                result.add(mutableListOf())
            }

            result[level].add(node.`val`)

            node.left?.also { queue.offer(level + 1 to it) }
            node.right?.also { queue.offer(level + 1 to it) }
        }

        return result
    }

    /**

        Edge cases:
            - root == null, empty list

        - starting on root
        - add val to a list
        - return lists result + (left + right) results

     */
    private fun dfs(
        root: TreeNode?,
        level: Int = 1,
        levels: TreeMap<Int, List<Int>> = TreeMap(),
    ): List<List<Int>> {
        if (root == null) return emptyList()

        levels[level] = levels.getOrDefault(level, listOf()) + listOf(root.`val`)

        dfs(root.left, level + 1, levels)
        dfs(root.right, level + 1, levels)

        return levels.values.toList()
    }
}
