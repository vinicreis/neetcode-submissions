/**
You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.

Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on a future day.

If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.
*/

class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        return bruteForce(temperatures)
    }

    /**
        temps =  [ ]
        result = [ ]

        temps =  [ 26 ]
        result = [  0 ]

        temps =  [ -3, 19, 30, 23, 31 ]
        result = [  1,  1,  2,  1,  0 ]

        temps =  [ 27, 19, 14, 4, -1 ]
        result = [  0,  0,  0, 0,  0 ]

                                    i
        temps =  [ -3, 19, 30, 23, 31 ]
        result = [  1,  1,  2,  1,  0 ]

        Intuition: brute force
            val results = IntArray(temps.size) { 0 }

            for i in temps.indices = traverse the temps
                val j = i + 1

                while (j < temps.lastIndex)
                    if (temps[j] > temp[i])
                        results[i] = j - i // Number of days until get warmer
                        break

                    j++

            return results

     */
    private fun bruteForce(temperatures: IntArray): IntArray {
        // Time:  O(n^2)
        // Space: O(n)
        val results = IntArray(temperatures.size) { 0 } // Space: O(n)

        for (i in temperatures.indices) { // O(n^2)
            var j = i + 1

            while (j <= temperatures.lastIndex) { // O(n)
                if (temperatures[j] > temperatures[i]) {
                    results[i] = j - i // Number of days until get warmer

                    break
                }

                j++
            }
        }

        return results
    }
}
