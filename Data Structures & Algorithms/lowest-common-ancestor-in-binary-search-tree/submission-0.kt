/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    /**

        Given a binary search tree (BST) where all node values are unique, 
        and two nodes from the tree p and q, return the lowest common ancestor (LCA) of the two nodes.

        The lowest common ancestor between two nodes p and q is the lowest node
        in a tree T such that both p and q as descendants.
         
        The ancestor is allowed to be a descendant of itself.

     */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        return solution(root, p, q)
    }

    /**

        - find a node in tree

     */
    private fun solution(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return null
        if (p == null) return null
        if (q == null) return null

        return when {
            max(p.`val`, q.`val`) < root.`val` -> solution(root.left, p, q)
            min(p.`val`, q.`val`) > root.`val` -> solution(root.right, p, q)
            else -> root
        }
    }
}
