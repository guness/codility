package a5.prefixSums

import kotlin.math.min

/**
A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).

For example, array A such that:

A[0] = 4
A[1] = 2
A[2] = 2
A[3] = 5
A[4] = 1
A[5] = 5
A[6] = 8
contains the following example slices:

slice (1, 2), whose average is (2 + 2) / 2 = 2;
slice (3, 4), whose average is (5 + 1) / 2 = 3;
slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
The goal is to find the starting position of a slice whose average is minimal.

Write a function:

fun solution(A: IntArray): Int
that, given a non-empty array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.

For example, given array A such that:

A[0] = 4
A[1] = 2
A[2] = 2
A[3] = 5
A[4] = 1
A[5] = 5
A[6] = 8
the function should return 1, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−10,000..10,000].

 */
fun main() {
    println(solution(intArrayOf(4, 2, 2, 5, 1, 5, 8)))
}

// O(N)
private fun solution(A: IntArray): Int {
    var index = -1
    var min = Double.MAX_VALUE

    A.indices.forEach {
        val avg = minWindowAverage(A, it) ?: Double.MAX_VALUE
        if (avg < min) {
            min = avg
            index = it
        }
    }
    return index
}

private fun minWindowAverage(A: IntArray, index: Int): Double? {
    A.getOrNull(index + 1)?.let { v1 ->
        val v0 = A[index]
        val avg2 = (v0 + v1) / 2.0
        A.getOrNull(index + 2)?.let { v2 ->
            return min(avg2, (v0 + v1 + v2) / 3.0)
        }
        return avg2
    }
    return null
}

// O(N***3)
private fun solutionA(A: IntArray): Int {
    val prefixSums = IntArray(A.size)
    A.indices.forEach {
        prefixSums[it] = prefixSums.getOrElse(it - 1) { 0 } + A[it]
    }

    val averages = IntRange(0, A.size - 2).map {
        val slices = calculateSliceAverages(it, prefixSums)
        slices.min()!!
    }
    return averages.indexOf(averages.min())
}

fun calculateSliceAverages(index: Int, prefixSums: IntArray): List<Double> {
    val list = mutableListOf<Double>()
    for (i in index + 1 until prefixSums.size) {
        val sum = getSum(index, i, prefixSums).toDouble()
        list.add(sum / (i - index + 1))
    }
    return list
}

fun getSum(start: Int, end: Int, prefixSums: IntArray): Int {
    return prefixSums[end] - prefixSums.getOrElse(start - 1) { 0 }
}


