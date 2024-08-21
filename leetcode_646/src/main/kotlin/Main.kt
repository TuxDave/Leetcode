fun main() {
    println(
        Solution().findLongestChain(
            arrayOf(
                intArrayOf(3, 4),
                intArrayOf(2, 3),
                intArrayOf(1, 2),
            )
        )
    )
}

class IntArrayComparator : Comparator<IntArray> {
    override fun compare(p0: IntArray, p1: IntArray): Int {
        return p0[1] - p1[1]
    }
}

@Suppress("NAME_SHADOWING")
class Solution {
    fun findLongestChain(pairs: Array<IntArray>): Int {
        val pairs = pairs.sortedWith { p0, p1 -> p0[1] - p1[1] }
        var last = pairs[0]
        var count = 1
        for (pair in pairs) {
            if (last[1] < pair[0]) {
                count++
                last = pair
            }
        }
        return count
    }
}