class Solution {
    /**
        There are n cars traveling to the same destination on a one-lane highway.

        You are given two arrays of integers position and speed, both of length n.

        position[i] is the position of the ith car (in miles)
        speed[i] is the speed of the ith car (in miles per hour)
        The destination is at position target miles.

        A car can not pass another car ahead of it. It can only catch up to another car and then drive at the same speed as the car ahead of it.

        A car fleet is a non-empty set of cars driving at the same position and same speed. A single car is also considered a car fleet.

        If a car catches up to a car fleet the moment the fleet reaches the destination, then the car is considered to be part of the fleet.

        Return the number of different car fleets that will arrive at the destination.
    */
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        return time(target, position, speed)
    }

    /**

        position -> target
        time -> distance / speed

        time = (target - position) / speed

        times = [ ]
     */
    fun time(target: Int, position: IntArray, speed: IntArray): Int {
        if (position.isEmpty()) return 0
        if (position.size != speed.size) return -1

        val cars = position.zip(speed).sortedByDescending { it.first }
        var fleets = 1
        var previousTime = (target - cars[0].first).toDouble() / cars[0].second
        
        for (i in 1 until cars.size) {
            val (position, speed) = cars[i]
            val time = (target - position).toDouble() / speed

            if (time > previousTime) {
                fleets++
                previousTime = time
            }
        }

        return fleets
    }
    
    /**
        n cars on 1-lane highway
        =====================================
         0 | 1 | 2 | 3 | ...        | target

            position[1]     position[0]
                           new_position[1] new_position[0]

        target      = 100
        position    = [ 50, 70 ]
        speed       = [ 50, 20 ]

            after (x) hour 
                1: car 1: will be at 90 (limited by 2)  :cross: not reached
                   car 2: will be at 90                 :cross: not reached
                
                2: car 1: will be at 140  :check: reached
                   car 2: will be at 140  :check: reached

        result = 1 (only on fleet arrived)

        target      = 100
        position    = [ 10, 30, 50, 70, 90 ]
        speed       = [ 15, 20, 50, 30,  5 ]

            after (x) hour 
                1: car 1: will be at 25                 :cross: not reached
                   car 2: will be at 50                 :cross: not reached
                   car 3: will be at 95 (limited by 4)  :cross: not reached
                   car 4: will be at 95 (limited by 5)  :cross: not reached
                   car 5: will be at 95                 :cross: not reached
                result = 0

                2: car 1: will be at 40                 :cross: not reached
                   car 2: will be at 70                 :cross: not reached
                   car 3: will be at 100                :check: reached
                   car 4: will be at 100                :check: reached
                   car 5: will be at 100                :check: reached
                result = 1

                3: car 1: will be at 55                 :cross: not reached
                   car 2: will be at 100                :check: reached
                   car 3: will be at 100                :check: reached
                   car 4: will be at 100                :check: reached
                   car 5: will be at 100                :check: reached
                result = 2

                ...

                result = 3

        result = 1 (only on fleet arrived)

        - x iterations until all cars reaches the destination
            - we have to assume, no cars are stopped, otherwise, it would never finish
            - on each iteration, position of the car adds up by speed, if we assume each iteration takes 1 hour
            - initially all cars compose fleets, even the ones alone
            - if the cars can not pass each other, all fleet runs at its minimum speed
            - consider the scenario where a car is stopped in any position
                - in this case, all other fleets behind, will not reach destination
        
        - first, we build the current fleets
            Fleet(
                cars: List<Int>, // card indexes
                speed: Int, // min of cars speeds
                currentPosition: Int, // position
            )
        - So, on each interaction, starting the most advanced fleet, we have to:
            - calculate the fleet's position
            - if there is a next fleet and it reached them
                if it does, merge them
        - after the iteration, check how many fleets target
            - unconsider them, consider them as one at the result
                
     */

    fun tooComplex(target: Int, position: IntArray, speed: IntArray): Int {
        data class Fleet(
            val cars: List<Int>,
            val speed: Int,
            val position: Int,
        )

        fun Fleets(
            positions: IntArray,
            speeds: IntArray,
        ): ArrayDeque<Fleet> = ArrayDeque<Fleet>().apply {
            positions.sorted().forEachIndexed { i, position ->
                val speed = speeds[i] ?: error("No speed for car in position $i index")

                this.firstOrNull { 
                    it.position == position
                }?.let { fleet ->
                    remove(fleet)

                    fleet.copy(
                        cars = fleet.cars + i,
                        speed = min(fleet.speed, speed)
                    ).also(::add)
                } ?: Fleet(cars = listOf(i), speed = speed, position = position).also(::add)
            }
        }

        operator fun Fleet.plus(other: Fleet) = Fleet(
            cars = cars + other.cars,
            speed = min(this.speed, other.speed),
            position = min(this.position, other.position),
        )

        val fleets = Fleets(position, speed)
        var result = 0
        // val fleets = Fleets(intArrayOf(10, 30, 30, 70, 90), intArrayOf(15, 20, 50, 30,  5))

        // println(fleets)

        while (fleets.isNotEmpty()) {
            val lastIndex = fleets.size - 1

            val fleet = fleets.removeLast()

            println("Fleet: $fleet")

            var updatedFleet = fleet.copy(
                position = fleet.position + fleet.speed,
            )

            println("Updated fleet: $updatedFleet")

            fleets.lastOrNull {
                it.position >= updatedFleet.position
            }?.also { nextFleet ->
                updatedFleet = updatedFleet + nextFleet
                fleets.remove(nextFleet)
                println("Removed next fleet: $nextFleet")
            }

            if (updatedFleet.position >= target) {
                result++; continue
            }

            fleets.addFirst(updatedFleet)
        }

        println("---")

        return result
    }
}
