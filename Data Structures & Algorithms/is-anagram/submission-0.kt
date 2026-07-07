class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        return solution1(s, t)
    }

    /**
        No nullables: Kotlin :cheers:
        Empty words? 
            - Both empty: Anagram :check:
            - One empty: No anagram :redcross:

        Examples:
            - ""        ""          true
            - "ana"     ""          false
            - "ana"     "nan"       false
            - "word"    "word"      true
            - "banana"  "ananab"    true
        
        1. Hash map:
            ~Create a occurrences hashmap for each word with each character frequency~
                - Time complexity: O(n)
                - Space complexity: O(n)

            Compare both hashmaps: O(n)

            --- 

            Create a occurrences hashmap for first word with each character frequency
                Time and space: O(n)
            Traverse the second word decrementing occurrences
                Time and space: O(n)

            Check if hash map is empty O(1)
                if yes,     return true
                otherwise,  return false
    */
    private fun solution1(s: String, t: String): Boolean {
        val occurrences = occurrencesOf(s)

        t.forEach { char -> occurrences.decrementOrRemove(char) }

        return occurrences.isEmpty()
    }

    private fun occurrencesOf(word: String): HashMap<Char, Int> {
        val occurrences = hashMapOf<Char, Int>()

        word.forEach { char -> 
            occurrences[char] = (occurrences[char] ?: 0) + 1
        }

        return occurrences
    }

    private fun <T> HashMap<T, Int>.decrementOrRemove(key: T) {
        when {
            containsKey(key).not() -> this[key] = -1
            this[key] == 1 -> remove(key)
            else -> this[key] = this[key]!! - 1
        }
    }
}
