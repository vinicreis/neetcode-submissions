class Solution {
    fun encode(strs: List<String>): String = buildString {
        strs.forEach { append("$DELIMITER${it.length}$DELIMITER$it") }
    }

    /**
        create a sizeText nullable string
        Iterate string while i < str.length
            when
                find a delimiter and sizeText == null -> initialize sizeText
                find a delimiter -> {
                    size finished
                    add substring(i + 1, i + 1 + size.toInt()) to result
                    sizeText = null
                    i = i + size.toInt()
                }
                sizeText != null -> add char to size
    */
    fun decode(str: String): List<String> {
        var i = 0
        val strs = mutableListOf<String>()
        var sizeText: String? = null

        while (i < str.length) {
            when {
                str[i] == DELIMITER && sizeText == null -> sizeText = ""
                str[i] == DELIMITER -> {
                    val size = sizeText!!.toInt()
                    val substring = str.substring(i + 1, i + 1 + size)

                    i = i + size
                    sizeText = null
                    strs.add(substring)
                }
                sizeText != null -> sizeText = sizeText!! + str[i].toString()
                else -> println("Shouldn't be on $i = ${str[i]}")
            }

            i++
        }

        return strs
    }

    private companion object {
        private const val DELIMITER = '%'
    }
}
