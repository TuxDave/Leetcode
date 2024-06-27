import java.io.File
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val f = File("file.txt")
    val l: MutableList<Int> = ArrayList()
    f.forEachLine {
        l.add(Integer.valueOf(it))
    }
    println(minAbsoluteDifference(l, 79811))
}

fun List<Int>.abs(i: Int, j: Int): Int {
    return abs(this[i] - this[j])
}

//TODO: con 100000 numeri non ce la fa
fun minAbsoluteDifference(nums: List<Int>, x: Int): Int {
    tailrec fun helper(nums: List<Int>, min: Int, offset: Int = 0): Int {
        if(nums.size > x) {
            tailrec fun abser(nums: List<Int>, i: Int, min: Int): Int { //min, offset
                if(nums.size == i) {
                    return min
                }
                val n = nums.abs(0, i)
                return if(n < min) {
                    abser(nums, i + 1, n)
                } else {
                    abser(nums, i + 1, min)
                }
            }
            val newMin = abser(nums, x, min)
            return if(newMin < min) {
                helper(nums.drop(1), newMin, offset + 1)
            } else {
                helper(nums.drop(1), min, offset + 1)
            }
        } else {
            return min
        }
    }
    return helper(nums, nums.abs(0, x), 0)
}