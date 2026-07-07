/**
You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.

Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on a future day.

If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.
*/

class Solution {
    /**
        Examples:

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
     */
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        return stack(temperatures)
    }

    /**
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

    /**
        temps =  [ -3, 19, 30, 23, 31 ]
        result = [  1,  1,  2,  1,  0 ]
              V
        [ -3, 19, 30, 23, 31 ]

        results:
            [ (temp: -3, day: 0, untilWarm: 0) ]
            [ ]
            [ (temp: -3, day: 0, untilWarm: 0) ]
        
        for (i, temp) in temps:
            val size = queue.size

            for j in 0 .. size:
                val queued = queue.poll()

                if (temp > queued.temp) {
                    results[queued.day] = queued.untilWarm + 1
                } else {
                    queue.addLast(
                        Data(
                            temp = queued.temp,
                            day = queued.day,
                            untilWarm = queued.untilWarm + 1,
                        )
                    )
                }

            queue.offer(
                Data(
                    temp = temp,
                    day = i,
                    untilWarm = 0,
                )
            )
    */
    private fun stack(temperatures: IntArray): IntArray {
        val results = IntArray(temperatures.size) { 0 } // Space: O(n)

        data class Data(
            val temp: Int,
            val day: Int,
            val untilWarm: Int = 0,
        )

        val stack = ArrayDeque<Data>()

        temperatures.forEachIndexed { i, temp ->
            val size = stack.size

            // println("Queue: $stack")

            for (j in 0 until size) {
                val queued = stack.removeFirst()

                // println("Queued on day $i: $queued")

                if (temp > queued.temp) {
                    results[queued.day] = queued.untilWarm + 1
                } else {
                    stack.addLast(queued.copy(untilWarm = queued.untilWarm + 1))
                }
            }

            // println("Results: ${results.toList()}")

            stack.addLast(Data(temp = temp, day = i))
        }

        return results
    }
}
