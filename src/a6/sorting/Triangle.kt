package a6.sorting

import kotlin.math.max
import kotlin.math.min

/**
An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

A[P] + A[Q] > A[R],
A[Q] + A[R] > A[P],
A[R] + A[P] > A[Q].
For example, consider array A such that:

A[0] = 10    A[1] = 2    A[2] = 5
A[3] = 1     A[4] = 8    A[5] = 20
Triplet (0, 2, 4) is triangular.

Write a function:

fun solution(A: IntArray): Int
that, given an array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.

For example, given array A such that:

A[0] = 10    A[1] = 2    A[2] = 5
A[3] = 1     A[4] = 8    A[5] = 20
the function should return 1, as explained above. Given array A such that:

A[0] = 10    A[1] = 50    A[2] = 5
A[3] = 1
the function should return 0.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 */
fun main() {
    println(solution(intArrayOf(10, 2, 5, 1, 8, 20)))
    println(solution(intArrayOf(10, 50, 5, 1)))
}

private fun solution(A: IntArray): Int {
    A.sort()
    for (i in 0 until A.size - 2) {
        if (isTriangleR(A[i], A[i + 1], A[i + 2])) {
            return 1
        }
    }
    return 0
}

fun isTriangleR(p: Int, q: Int, r: Int): Boolean {
    return p.toLong().plus(q) > r
}

/*
 O(N***3), also 62%
 */
private fun solutionB(A: IntArray): Int {
    A.sort()
    for (indexA in 0 until A.size - 2) {
        for (indexB in indexA + 1 until A.size - 1) {
            for (indexC in indexB + 1 until A.size) {
                if (isTriangle(A[indexA], A[indexB], A[indexC])) {
                    return 1
                }
            }
        }
    }
    return 0
}

fun isTriangle(a: Int, b: Int, c: Int): Boolean {
    return a + b > c && b + c > a && a + c > b
}

/**
 * returns Pair(min,max) values
 */
fun limitTriangle(a: Int, b: Int): Pair<Int, Int> {
    return Pair(max(a, b) - min(a, b) + 1, a + b - 1)
}
