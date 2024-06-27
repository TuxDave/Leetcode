fun main() {
    println(numSteps("1000101011101101001100011010111111011111010110100001101110110100000011100100010010101110101010010110000111001011000011000000011010101011100111001001110010000011101"))
}

fun numSteps(s: String): Int {
    if (s == "1") {
        return 0
    }
    return 1 + numSteps(if (s.last() == '0') div(s) else inc(s))
}

fun div(s: String): String {
    return s.dropLast(1)
}

fun inc(s: String): String {
    if (s.isEmpty()) return "1"
    if (s.last() == '0') {
        return s.dropLast(1) + "1"
    } else { //se è 1
        return inc(s.dropLast(1)) + "0"
    }
}