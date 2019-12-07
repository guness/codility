package a7.stacksAndQueues

import java.util.*

/**
You are going to build a stone wall. The wall should be straight and N meters long, and its thickness should be constant; however, it should have different heights in different places. The height of the wall is specified by an array H of N positive integers. H[I] is the height of the wall from I to I+1 meters to the right of its left end. In particular, H[0] is the height of the wall's left end and H[N−1] is the height of the wall's right end.

The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular). Your task is to compute the minimum number of blocks needed to build the wall.

Write a function:

fun solution(H: IntArray): Int
that, given an array H of N positive integers specifying the height of the wall, returns the minimum number of blocks needed to build it.

For example, given array H containing N = 9 integers:

H[0] = 8    H[1] = 8    H[2] = 5
H[3] = 7    H[4] = 9    H[5] = 8
H[6] = 7    H[7] = 4    H[8] = 8
the function should return 7. The figure shows one possible arrangement of seven blocks.



Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array H is an integer within the range [1..1,000,000,000].
 */
fun main() {
    println(solution(intArrayOf(8, 8, 5, 7, 9, 8, 7, 4, 8)))
}

private fun solution(H: IntArray): Int {
    val stack = Stack<Int>()
    var blocks = 0
    H.forEach {
        while (stack.isNotEmpty() && it < stack.peek()) {
            stack.pop()
        }
        if (stack.empty() || stack.peek() != it) {
            stack.push(it)
            blocks++
        }
    }
    return blocks
}

// 92%
private fun solutionB(H: IntArray): Int {
    val stack = Stack<Int>()
    var closing = 0
    H.forEach {
        if (stack.empty() || it > stack.peek()) {
            stack.push(it)
        } else {
            if (stack.peek() != it) {
                while (stack.isNotEmpty() && it < stack.peek()) {
                    stack.pop()
                    closing++
                }
                if (stack.isEmpty() || it != stack.peek()) {
                    stack.push(it)
                }
            }
        }
    }
    return closing + stack.size
}
