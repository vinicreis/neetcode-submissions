class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        return solution2(board)
    }

    /**
        Only valid boards??

        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        ----------------|---------------|----------------    
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        ----------------|---------------|----------------
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]
        [ "1", "2", "3" | "4", "5", "6" | "7", "8", "9" ]

        - iterate rows in i
            - iterate columns on j
                - check row
                - check column
                - check quadrant
            
        or

        - cluster board in three checks
            - cluster by row
            - cluster columns
            - cluster quadrants
        - check if clusters are valid

        - Common problems to be split and conquered:
            - get the row of a specific cell :check: trivial
                = board[i] where i = cell x index
            - get the column of a specific cell =
                = all board[x][i] 
                    where   x is 0 ..< 9 
                            i cell column index
            - get the quadrant of a specific cell
                = for a cell in (x, y)
                    iterate i in 0 .. 9
                        iterate j in 0 .. 9
                            quadrant.add(board[x / 3 + i % 3][y / 3 + j % 3])
     */
    private fun solution1(board: Array<CharArray>): Boolean {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board.quadrantOf(i, j).hasRepeated()) return false
                if (board.rowOf(i, j).hasRepeated()) return false
                if (board.columnOf(i, j).hasRepeated()) return false
            }
        }

        return true
    }

    /**
        Solution 1 works, but its repeating a lot of checks, because it's checking all cells
        not all important pieces
     */
    private fun solution2(board: Array<CharArray>): Boolean {
        /**
            Genarate the points
                - (0, 0)
                - (0, 3)
                - (0, 6)
                - (3, 0)
                - (3, 3)
                -  ...
        */
        val quadrants = buildList {
            for (i in board.indices) {
                val m = 3 * (i / 3)
                val n = 3 * (i % 3)

                add(m to n)
            }
        }

        for (i in board.indices) {
            if (board.rowOf(i, 0).hasRepeated()) return false
            if (board.columnOf(0, i).hasRepeated()) return false

            val (x, y) = quadrants[i]
            if (board.quadrantOf(x, y).hasRepeated()) return false
        }

        return true
    }

    private fun Array<CharArray>.rowOf(x: Int, y: Int): CharArray = this[x]
    private fun Array<CharArray>.columnOf(x: Int, y: Int): CharArray {
        val column = CharArray(size)

        for (i in this.indices) {
            column[i] = this[i][y]
        }

        return column
    }

    private fun Array<CharArray>.quadrantOf(x: Int, y: Int): CharArray {
        val quadrant = CharArray(size)

        for (i in this.indices) {
            val m = 3 * (x / 3) + i / 3
            val n = 3 * (y / 3) + i % 3

            quadrant[i] = this[m][n]
        }

        return quadrant
    }

    private fun CharArray.hasRepeated(): Boolean {
        var frequency = mutableListOf<Char>()

        for (i in indices) {
            if (this[i] == '.') continue

            if (this[i] in frequency) {
                return true
            }

            frequency.add(this[i])
        }
        
        return false
    }
}
