package a7.stacksAndQueues

/**
A string S consisting of N characters is called properly nested if:

S is empty;
S has the form "(U)" where U is a properly nested string;
S has the form "VW" where V and W are properly nested strings.
For example, string "(()(())())" is properly nested but string "())" isn't.

Write a function:

fun solution(S: String): Int
that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.

For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..1,000,000];
string S consists only of the characters "(" and/or ")".
 */
fun main() {
    println(solution("(()(())())"))
    println(solution("())"))
}

private fun solution(S: String): Int {
    if (S.length.rem(2) == 1) return 0
    var stack = 0
    S.forEach {
        when (it) {
            '(' -> stack++
            ')' -> stack--
            else -> throw IllegalArgumentException()
        }
        if (stack < 0) return 0
    }
    if (stack > 0) return 0
    return 1
}
