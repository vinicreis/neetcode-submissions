/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return recursive(p, q)
    }

    /**

        Edge cases:
            - If both roots == null, return true
            - If any null, return false

        Recursive
            - if p.`val` != q.`val` return false
            - return call(p.right, q.right) && call(p.left, q.left)
     */
    private fun recursive(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null) return false
        if (q == null) return false

        if (p.`val` != q.`val`) return false

        return recursive(p.left, q.left) && recursive(p.right, q.right)
    }
}
