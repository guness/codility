package exam

import kotlin.math.min

fun main() {
    println(solution(intArrayOf(5, 4, 3, 2, 6)))
    println(solution(intArrayOf(3, 7, 4, 5)))
}

private fun solution(A: IntArray): Int {
    var cuts1 = 0
    for (i in A.indices step 2) {
        if (needsCut(A, i)) {
            cuts1++
        }
    }
    var cuts2 = 0
    for (i in 1 until A.size step 2) {
        if (needsCut(A, i)) {
            cuts2++
        }
    }
    return min(cuts1, cuts2)
}

private fun needsCut(A: IntArray, index: Int): Boolean {
    val leftOk = A.getOrNull(index - 1)?.let { it > A[index] } ?: true
    val rightOk = A.getOrNull(index + 1)?.let { it > A[index] } ?: true
    return !leftOk || !rightOk
}
