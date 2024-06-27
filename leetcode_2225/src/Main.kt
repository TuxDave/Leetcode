fun findWinners(matches: Array<IntArray>): List<List<Int>> {
    val loser: HashMap<Int, Int> = HashMap()
    matches.map{it[1]}.forEach { loser[it] = (loser[it] ?: 0) + 1 }
    val l0 = matches
        .map { it[0] }
        .toSet()
        .filter { !loser.containsKey(it) }.sorted()
    val l1 = loser.keys.toSet().filter { loser[it] == 1 }.sorted()
    return listOf(l0, l1)
}