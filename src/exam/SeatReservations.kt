package exam

fun main() {
    println(solution(2, "1A 2F 1C"))
    println(solution(1, ""))
}

private fun solution(N: Int, S: String): Int {
    val seatMap = createSeatMap(N, S)
    var seats4 = 0
    seatMap.forEach { bc, de, fg, hj ->
        when {
            bc && de && fg && hj -> seats4 += 2
            (bc && de) || (de && fg) || (fg && hj) -> seats4 += 1
        }
    }
    return seats4
}

private fun createSeatMap(N: Int, S: String): SeatMap {
    val seatMap = SeatMap(N)
    S.split(" ").filter { it.isNotEmpty() }.forEach {
        val seat = it.takeLast(1)
        val number = it.take(it.length - 1).toInt() - 1
        when (seat) {
            "B", "C" -> seatMap.bc[number] = false
            "D", "E" -> seatMap.de[number] = false
            "F", "G" -> seatMap.fg[number] = false
            "H", "J" -> seatMap.hj[number] = false
        }
    }
    return seatMap
}

// Keeps available seats
private class SeatMap(val N: Int) {
    val bc = BooleanArray(N) { true }
    val de = BooleanArray(N) { true }
    val fg = BooleanArray(N) { true }
    val hj = BooleanArray(N) { true }

    inline fun forEach(action: (Boolean, Boolean, Boolean, Boolean) -> Unit): Unit {
        for (i in 0 until N) {
            action(bc[i], de[i], fg[i], hj[i])
        }
    }
}
