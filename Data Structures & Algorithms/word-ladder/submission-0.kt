class Solution {
    fun ladderLength(beginWord: String, endWord: String, wordList: MutableList<String>): Int {
        return solution(beginWord, endWord, wordList)
    }

    /**

        a helper to count differences between to strings

        think this as graph
        start with some search among words: dfs
        keep a visited set for each search
        during search, when the words are equal, return the path size

        result = 0

        dfs(i: Int, visited = hashSet(), path: Int = 1)
            if wordList[i] == endWord, return true
            if wordList[i] in visited, return false

            visited.add(wordList[i])

            for (j, word) in wordList.withIndex
                if word == wordList[i], continue
                if word diffInMoreThanOneWith wordList[i] continue
                
                if (dfs(j, visited, path + 1)) return true

            return false

        for ((i, word) in wordList.withIndex)
            visited = hashSet<String>
            dfs(word, visited)

        return result

     */
    private fun solution(beginWord: String, endWord: String, wordList: MutableList<String>): Int {
        if (wordList.isEmpty()) return 0
        if (endWord !in wordList) return 0
        
        var result = Int.MAX_VALUE

        fun dfs(current: String, visited: HashSet<String>): Boolean {
            if (current in visited) return false

            visited.add(current)
            
            if (current == endWord) return true

            for ((j, next) in wordList.withIndex()) {
                if (current == next) continue
                if (current differsInOtherThanOneFrom next) continue

                if (dfs(next, visited)) return true
            }

            return false
        }

        for ((i, word) in wordList.withIndex()) {
            val visited = hashSetOf<String>()

            if (dfs(beginWord, visited)) result = min(result, visited.size)

            println("Visited: $visited")
        }

        return result
    }

    private infix fun String.differsInOtherThanOneFrom(other: String): Boolean {
        if (this.isEmpty()) return true
        if (length != other.length) return true

        var changes = 0

        for (i in 0 until length) {
            if (this[i] != other[i]) changes++
        }

        return changes != 1
    }
}
