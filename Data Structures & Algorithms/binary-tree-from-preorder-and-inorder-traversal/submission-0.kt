/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return solution(preorder.toList(), inorder.toList())
    }

    private fun solution(
        preorder: List<Int>,
        inorder: List<Int>,
    ): TreeNode? {
        if (preorder.isEmpty()) return null
        if (inorder.isEmpty()) return null

        val root = TreeNode(preorder[0])
        var mid = inorder.indexOf(preorder[0])

        root.left = solution(preorder.slice(1 .. mid), inorder.slice(0 .. mid - 1))
        root.right = solution(preorder.slice(mid + 1 .. preorder.lastIndex), inorder.slice(mid + 1 .. inorder.lastIndex))

        return root
    }
}
