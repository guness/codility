package a4.countingElements

import java.util.*

/**
This is a demo task.

Write a function:

fun solution(A: IntArray): Int
that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 */

fun main() {
    println(solution(intArrayOf(1, 3, 6, 4, 1, 2)))
}

private fun solution(A: IntArray): Int {
    val array = BooleanArray(A.size)
    A.forEach {
        if (it > 0 && it <= A.size) {
            array[it - 1] = true
        }
    }

    val firstFalse = array.indexOfFirst { !it }
    val firstTrue = array.indexOfFirst { it }

    return 1 + when {
        firstFalse == -1 && firstTrue == -1 -> 0
        firstFalse == -1 -> A.size
        else -> firstFalse
    }
}
