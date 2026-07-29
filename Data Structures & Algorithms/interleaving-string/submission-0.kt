class Solution {
    /**

        Edge cases:
            - if s1 is empty, return s2 == s3
            - if s2 is empty, return s1 == s3
            - if s3 is empty, return s1 is empty && s2.isEmpty()

     */
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.isEmpty()) return s2 == s3
        if (s2.isEmpty()) return s2 == s3
        if (s3.isEmpty()) return s2.isEmpty() && s1.isEmpty()

        return intuition(s1, s2, s3)
    }

    /**

        - for each s1 index = m, treat it as middle
        - iterate s3 indexes, t

        m = 0
        m = 1
        m = 2
                          
        s1 = "aaaa" s2 = "bbbb"
                    t  
        s3 = "aabbbbaa"
        t = 7
        m = 2
        s2.lastIndex = 3
        6 - 3 - 1 = 2

        if t < m:                              check if s3[t] == s1[t]
        if t >= m && t < m + s2.lastIndex - 1: check if s3[t] == s2[t - m]
        otherwise:                             check if s3[t] == s1[t - s2.lastIndex - 1]

     */
    private fun intuition(s1: String, s2: String, s3: String): Boolean {
        fun dfs(m: Int = 0): Boolean {
            if (m > s1.lastIndex) return false

            println("Running middle on $m")

            for (t in s3.indices) {
                when {      
                    t < m -> println("Comparing first indices on s1: s3[$t] != s1[$t]")
                    t > m + s2.length -> println("Comparing last indices on s1: s3[$t] != s1[${t - m - s2.length}]")
                    else -> println("Comparing middle indices on s2: s3[$t] != s1[${t - m}]")
                }

                // when {
                //     t < m -> if (s3[t] != s1[t]) return false
                //     t > m + s2.length -> if (s3[t] != s1[t - m - s2.length]) return false
                //     else -> if (s3[t] != s2[t - m]) return false
                // }
            }
            

            return true
        }

        for (i in s1.indices) {
            if (dfs(i)) return true
        }

        return false
    }
}
