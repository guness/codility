package exam

fun main() {
    println(solution(arrayOf(intArrayOf(0, 1, 5, 0, 0))))
}

private fun solution(Board: Array<IntArray>): Int {
    for (start in 9 downTo 1) {
        val maxValue = maxValueOf(Board, start)
        if (maxValue > 0) return maxValue
    }
    return 0
}

// Returns max value starting with number X, or -1 if none
private fun maxValueOf(Board: Array<IntArray>, X: Int): Int {
    var max = -1
    Board.forEachIndexed { i, j, xCandidate ->
        if (X == xCandidate) {
            val value = maxValueOf(Board, i, j)
            if (value > max) {
                max = value
            }
        }
    }
    return max
}


// Returns max value starting from point.
private fun maxValueOf(Board: Array<IntArray>, i: Int, j: Int): Int {
    var X = Board[i][j]
    val exclusions = mutableListOf(Pair(i, j))

    var next = maxNext(Board, i, j, exclusions)
    X = X * 10 + next.third
    exclusions += Pair(next.first, next.second)

    next = maxNext(Board, i, j, exclusions)
    X = X * 10 + next.third
    exclusions += Pair(next.first, next.second)

    next = maxNext(Board, i, j, exclusions)
    X = X * 10 + next.third
    exclusions += Pair(next.first, next.second)

    return X
}

// Returns value, i, j of the value
private fun maxNext(Board: Array<IntArray>, i: Int, j: Int, exclusions: List<Pair<Int, Int>>): Triple<Int, Int, Int> {
    var max = -1
    var pair = Pair(0, 0)
    Pair(i, j).forEachIndex(Board.size, Board[0].size) { ix, jx ->
        if (!exclusions.contains(Pair(ix, jx))) {
            val value = Board[ix][jx]
            if (value > max) {
                max = value
                pair = Pair(ix, jx)
            }
        }
    }
    return Triple(pair.first, pair.second, max)
}

private inline fun Array<IntArray>.forEachIndexed(action: (Int, Int, Int) -> Unit) {
    this.forEachIndexed { indexI, ints ->
        ints.forEachIndexed { indexJ, X ->
            action(indexI, indexJ, X)
        }
    }
}

private fun Pair<Int, Int>.forEachIndex(M: Int, N: Int, action: (Int, Int) -> Unit) {
    if (first - 1 >= 0) action(first - 1, second)
    if (first - 1 >= 0 && second - 1 >= 0) action(first - 1, second - 1)
    if (second - 1 >= 0) action(first, second - 1)
    if (first + 1 < M && second - 1 >= 0) action(first + 1, second - 1)
    if (first + 1 < M) action(first + 1, second)
    if (first + 1 < M && second + 1 < N) action(first + 1, second + 1)
    if (second + 1 < N) action(first, second + 1)
    if (first - 1 >= 0 && second + 1 < N) action(first - 1, second + 1)
}
