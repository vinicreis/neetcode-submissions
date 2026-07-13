/*
Definition for a Node.
class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}
*/

class Solution {
    fun cloneGraph(node: Node?): Node? {
        return bfs(node)
    }

    /**

        if node is null, return null
        save copied nodes, hashmap
        create a queue of copies
        copy root
        add root copy to queue

        while queue is not empty
            current = poll queue

            foreach neighbor
                if not copied
                    copy it
                    add to queue

                add copy to neighbors
        
        return root
     */
    private fun bfs(node: Node?): Node? {
        if (node == null) return null // or node

        val copies = hashMapOf<Node, Node>()
        val queue = LinkedList<Pair<Node, Node>>()
        val rootCopy = Node(node.`val`)

        copies[node] = rootCopy
        queue.offer(node to rootCopy)

        while (queue.isNotEmpty()) {
            val (original, copy) = queue.poll()

            original.neighbors.forEach { neighbor ->
                val neighborCopy = if (copies.containsKey(neighbor)) {
                    copies[neighbor]
                } else {
                    Node(neighbor!!.`val`).also { neighborCopy -> 
                        copies[neighbor!!] = neighborCopy
                        queue.offer(neighbor!! to neighborCopy) 
                    }
                }

                copy.neighbors = ArrayList(copy.neighbors.toList() + neighborCopy)
            }
        }

        return rootCopy
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
    private fun dfs(node: Node?): Node? {
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
