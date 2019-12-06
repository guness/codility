package a5.prefixSums

/**
Write a function:

fun solution(A: Int, B: Int, K: Int): Int

that, given three integers A, B and K, returns the number of integers within the range [A\..B] that are divisible by K, i.e.:

{ i : A ≤ i ≤ B, i mod K = 0 }

For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.

Write an efficient algorithm for the following assumptions:

A and B are integers within the range [0..2,000,000,000];
K is an integer within the range [1..2,000,000,000];
A ≤ B.
 */
fun main() {
    println("2: " + solution(10, 20, 10))
    println("3: " + solution(10, 30, 10))
    println("2: " + solution(11, 30, 10))
    println("1: " + solution(11, 29, 10))
    println("2: " + solution(10, 29, 10))
    println("1: " + solution(20, 20, 10))
    println("1: " + solution(14, 29, 10))
    println("1: " + solution(15, 29, 10))
    println("1: " + solution(16, 29, 10))
}

// 100
private fun solution(A: Int, B: Int, K: Int): Int {
    val start = findStart(A, K)
    val end = findEnd(B, K)
    return ((end - start) / K) + 1
}

fun findStart(A: Int, K: Int): Int {
    val rem = A.rem(K)
    return if (rem == 0) {
        A
    } else {
        A + (K - rem)
    }
}

fun findEnd(B: Int, K: Int): Int {
    val rem = B.rem(K)
    return if (rem == 0) {
        B
    } else {
        B - rem
    }
}


// 87% score
private fun solutionB(A: Int, B: Int, K: Int): Int {
    val start = if (A % K == 0) 1 else 0
    val end = if (B % K == 0) 1 else 0
    val addition = if (start + end > 0) 1 else 0
    return (B - A) / K + addition
}
