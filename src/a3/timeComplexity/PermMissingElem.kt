package a3.timeComplexity

/**
An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.

Your goal is to find that missing element.

Write a function:

fun solution(A: IntArray): Int
that, given an array A, returns the value of the missing element.

For example, given array A such that:

A[0] = 2
A[1] = 3
A[2] = 1
A[3] = 5
the function should return 4, as it is the missing element.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
the elements of A are all distinct;
each element of array A is an integer within the range [1..(N + 1)].

 */
fun main() {
    println(solution(intArrayOf(2)))
}

private fun solution(A: IntArray): Int {
    var result = if (A.size.rem(2) == 0) -(A.size + 1) else 0
    A.forEach {
        if (it.rem(2) == 0) {
            result -= (it - 1)
        } else {
            result += it
        }
    }

    return if (result < 0) -result else result + 1
}
