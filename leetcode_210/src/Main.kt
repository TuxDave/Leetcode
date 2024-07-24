import java.util.*

fun IntArray.swap(left: Int, right: Int) {
    val temp = this[left]
    this[left] = this[right]
    this[right] = temp
}

private enum class Color {
    WHITE,
    GREY,
    BLACK
}

fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val colors = Array(numCourses) { Color.WHITE }
    val closes = IntArray(numCourses) { 0 }
    val edges = prerequisites
        .map { intArrayOf(it[1], it[0]) }
        .toMutableList()
    val stack: Stack<Int> = Stack<Int>()

    var closeTime = -1

    while (edges.any { colors[it[0]] == Color.WHITE }) {
        var vertex = edges.first()[0]
        colors[vertex] = Color.GREY
        closeTime++
        stack.push(vertex)
        var c = 0
        while (stack.isNotEmpty()) {
            vertex = stack.peek()
            val next = edges.find { it[0] == vertex && colors[it[1]] != Color.BLACK }
            if (next != null) {
                edges.remove(next)
                if (colors[next[1]] == Color.GREY) {
                    return intArrayOf()
                } else {
                    stack.push(next[1])
                    colors[stack.peek()] = Color.GREY
                    closeTime++
                }
            } else {
                colors[vertex] = Color.BLACK
                closes[vertex] = ++closeTime
                stack.pop()
                if(c == 0) {
                    edges.removeAt(0)
                }
            }
            c++
        }
    }
    return closes.mapIndexed { index, i ->
        Pair(index, i)
    }.toList().sortedBy { it.second }.map { it.first }.toIntArray().reversedArray()
}

//TODO: fare test case: 8, [[1,0],[2,6],[1,7],[6,4],[7,0],[0,5]]

fun main() {
    println(findOrder(4, arrayOf(intArrayOf(1,0), intArrayOf(2,0), intArrayOf(3,1), intArrayOf(3,2))).toList())
}