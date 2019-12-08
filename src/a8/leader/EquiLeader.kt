package a8.leader

/**
A non-empty array A consisting of N integers is given.

The leader of this array is the value that occurs in more than half of the elements of A.

An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.

For example, given array A such that:

A[0] = 4
A[1] = 3
A[2] = 4
A[3] = 4
A[4] = 4
A[5] = 2
we can find two equi leaders:

0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
The goal is to count the number of equi leaders.

Write a function:

fun solution(A: IntArray): Int
that, given a non-empty array A consisting of N integers, returns the number of equi leaders.

For example, given:

A[0] = 4
A[1] = 3
A[2] = 4
A[3] = 4
A[4] = 4
A[5] = 2
the function should return 2, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 */
fun main() {
    println(solution(intArrayOf(4, 3, 4, 4, 4, 2)))
}

private fun solution(A: IntArray): Int {
    val leader = findLeader(A) ?: return 0
    var eQuiCount = 0
    var numLeaders = 0
    A.forEachIndexed { index, i ->
        if (i == leader.first) {
            numLeaders++
        }
        if (numLeaders * 2 > (index + 1) && (leader.second - numLeaders) * 2 > (A.size - index - 1)) {
            eQuiCount++
        }
    }
    return eQuiCount
}

/**
 * returns leader, and count
 */
private fun findLeader(A: IntArray): Pair<Int, Int>? {
    val map = mutableMapOf<Int, Int>()
    A.forEach {
        map[it] = (map[it] ?: 0) + 1
    }
    val entry = map.maxBy {
        it.value
    }
    return if (2 * (entry?.value ?: 0) > A.size) {
        entry?.let { Pair(it.key, it.value) }
    } else {
        null
    }
}
