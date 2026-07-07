private typealias OccurrenceMap = HashMap<Char, Int>
private typealias AnagramSubgroups = HashMap<String, List<String>>

class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return solution1(strs)
    }

    /**
        - Empty input? Empty result
        - Empty words? Can its' own subset
        - Equals words? Are anagrams

        - [ "", "", "", "car", "ball", "football", "race", "acer" ]
        - [ [ "", "", "" ], [ "car" ], [ "ball" ], [ "football" ], [ "race", "acer" ] ]

        - split problem
            - How a word is an anagram of other?
                - Contains the same letters, considering its occurrences
                - OccurrenceMap = HashMap<Char, Int>
            - How could we group them in subgroups?
                - Comparing hashmaps? O(n) - seems ok!
            - How we store subgroups?
                - What could be the key?
                - First word? seems ok
            
        - With this, I can
            n = words list
            m = word length

            - build the AnagramSubgroup :check:
            - iterate the words O(n)
                - build its occurrence map O(m)
                - check if any key on subgroups is anagram of current word with the map O(m)
                    - if so, add to its subgroup - selecting by the matched key
                    - if not, add it as new subgroup
            - convert and return subgroups :check:
    */

    private fun solution1(strs: Array<String>): List<List<String>> {
        val subgroups: AnagramSubgroups = hashMapOf()

        strs.forEach { word ->
            subgroups.insertOrCreate(word)

            // println("Map for \"$word\": $occurrenceMap")
            // println("Equals: ${"random".occurrenceMap() isAnagramOf occurrenceMap}")
            // println("Subgroups: ${subgroups}")
        }

        return subgroups.toNestedList()
    }

    private fun String.occurrenceMap(): OccurrenceMap {
        val map = hashMapOf<Char, Int>()

        forEach { char -> 
            map[char] = (map[char] ?: 0) + 1
        }

        return map
    }

    private infix fun OccurrenceMap.isAnagramOf(other: OccurrenceMap): Boolean {
        if (size != other.size) return false
        if(keys.all { k -> containsKey(k) }.not()) return false

        forEach { (key, value) ->
            if (other[key] != this[key]) return false
        }

        return true
    }

    private fun AnagramSubgroups.keyFor(word: String): String? {
        return keys.firstOrNull { key -> key.occurrenceMap() isAnagramOf word.occurrenceMap() }
    }

    private fun AnagramSubgroups.insertOrCreate(word: String) {
        keyFor(word)
            ?.also { key -> this[key] = this[key]!! + word }
            ?: run { this[word] = listOf(word) }
    }

    private fun AnagramSubgroups.toNestedList() = map { (_, subgroup) -> subgroup }
}
