/*
Definition for a Node.
class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}
*/

class Solution {
    fun cloneGraph(node: Node?): Node? {
        return solution(node)
    }

    /**

        - traverse all graph: DFS
        - save copied nodes, hashmap

        - if node is null, return null
        - for each node
            - if copied, return copy
            - create a copy
            - recursively copy neighbors
            - link the neighbors to created copy
            - save copy on map
            - return copy

     */
    private fun solution(node: Node?): Node? {
        if (node == null) return null

        val copies = hashMapOf<Node, Node>()

        fun Node?.deepCopy(): Node? {
            if (this == null) return null
            if (copies.containsKey(this)) return copies[this]

            return Node(`val`).apply { 
                copies[this@deepCopy] = this
                this.neighbors = this@deepCopy.neighbors.map { it.deepCopy() }.let(::ArrayList)
            }
        }

        return node.deepCopy()
    }
}
