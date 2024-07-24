import jdk.internal.net.http.common.Pair.pair
import java.util.*

fun main() {
    val r = shortestBridge(
        arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 0, 0, 0, 1),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(1, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1),
        )
//        arrayOf(
//            intArrayOf(0, 1),
//            intArrayOf(1, 0)
//        )
    )
    println(r)
}

//TODO: recursivize this
fun shortestBridge(grid: Array<IntArray>): Int {
    val n = grid.size
    val nm = n - 1

    val start = fun(): Pair<Int, Int> {
        for (i in 0..<n) {
            for (j in 0..<n) {
                if (grid[i][j] == 1) {
                    return Pair(i, j)
                }
            }
        }
        throw IllegalArgumentException("no islands")
    }.invoke()

    val island: MutableSet<Pair<Int, Int>> = HashSet()
    val news: Queue<Pair<Int, Int>> = LinkedList()
    island.add(start)
    news.add(start)

    while (news.isNotEmpty()) {
        val spot = news.poll()
        for (j in -1..1 step 2) {
            val ps = arrayOf(Pair(spot.first, spot.second + j), Pair(spot.first + j, spot.second))
            for (p in ps) {
                if (p.first in 0..<n && p.second in 0..<n) {
                    if (grid[p.first][p.second] == 1) {
                        if (island.add(p)) {
                            news.add(p)
                        }
                    }
                }
            }
        }
    }

    var pathLength = 0
    var base: MutableSet<Pair<Int, Int>> = HashSet(island)
    var discovered: MutableSet<Pair<Int, Int>> = HashSet()
    val found: MutableSet<Pair<Int, Int>> = HashSet(island)
    while(true) {
        for (spot in base) {
            for (j in -1..1 step 2) {
                val ps = arrayOf(Pair(spot.first, spot.second + j), Pair(spot.first + j, spot.second))
                for (p in ps) {
                    if (p.first in 0..<n && p.second in 0..<n) {
                        if (!found.contains(p) && !base.contains(p)) {
                            if(grid[p.first][p.second] == 1) {
                                return pathLength
                            } else {
                                discovered.add(p)
                                found.add(p)
                            }
                        }
                    }
                }
            }
        }
        pathLength++
        base = HashSet(discovered)
        discovered = HashSet()
    }
}