class Solution {
    /**

        You are given an integer array piles where piles[i] is the number of bananas in the ith pile.
        
        You are also given an integer h, which represents the number of hours you have to eat all the bananas.

        You may decide your bananas-per-hour eating rate of k.
        
        Each hour, you may choose a pile of bananas and eats k bananas from that pile.
        
        If the pile has less than k bananas, you may finish eating the pile but you can not eat from another pile in the same hour.

        Return the minimum integer k such that you can eat all the bananas within h hours.

     */
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        return binarySearch(piles, h)
    }

    /**

     */
    private fun binarySearch(piles: IntArray, h: Int): Int {
        var min = 1
        var max = piles.max()
        var result = 1

        while (min <= max) {
            val k = min + (max - min) / 2
            var hours = 0

            piles.forEach { pile ->
                hours += ceil(pile.toDouble() / k).toInt()
            }

            if (hours > h) {
                min = k + 1
            } else {
                result = k
                max = result - 1
            }
        }

        return result
    }

    /**
        Edge cases
            - piles are empty, no bananas, so k = 0
            - when h = 0, then, k = 0 or +Inf
            - if a pile is empty, just skip it

        result = k = a number of bananas which I'll eat from a pile, without switching pile

        goal is to each reach minimum k and eat all bananas

        - k too high, will make me stop at lower piles

        piles = [ 3, 4, 9, 1, 2, 4 ]
            h = 10
            total = 23

            23 / 10 = 2.3 round up to 3
            = this might give us a floor

            the piles size might be useful

            I have always more or equals hours then piles

            would be the same as doing: [ 3, 3, 1, 3, 3, 3, 1, 2, 3, 1 ]

            h = 6
            23 / 6 = ~3.8999 = round up to 4

            [ 3, 4, 4, 4, 1, 1, 2, 4 ]

            try 5

            [ 3, 4, 5, 4, 1, 2, 4 ]
            [ 3, 4, 6, 3, 1, 2, 4 ]
            [ 3, 4, 9, 1, 2, 4 ]

        find the total bananas // O(n)
        find a start minimum k, by total / hours
        build the eating array = eatings

        while (eatings.size > hours)
            increase k
            rebuild it

        return k
     */
    private fun solution(piles: IntArray, h: Int): Int {
        if (piles.isEmpty()) return 0
        if (h <= 0) return 0

        var k = piles.minimalKFor(h)
        var eatings = piles.eatingsFor(k)
        // val eatings = intArrayOf(3, 4, 9, 1, 2, 4).eatingsFor(4)

        while (eatings.size > h) {
            // println("Eatings for $k in $h hours: $eatings")

            eatings = piles.eatingsFor(++k)

            // println("Eatings for $k in $h hours: $eatings")
        }

        return k
    }

    private fun IntArray.minimalKFor(hours: Int): Int {
        return ceil(sum().toDouble() / hours).toInt()
    }

    private fun IntArray.eatingsFor(k: Int): List<Int> {
        if (isEmpty()) return emptyList()

        val copy = IntArray(size) { this[it] }
        val eatings = mutableListOf<Int>()
        
        var i = 0

        while (i <= lastIndex) {
            if (copy[i] <= k) {
                eatings.add(copy[i++])
            } else {
                copy[i] = copy[i] - k
                eatings.add(k)
            }

            // println("Curr eatings: $eatings, for piles ${this.toList()}")
        }

        return eatings
    }
}
