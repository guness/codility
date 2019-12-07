package a7.stacksAndQueues

import java.lang.IllegalArgumentException
import java.util.*

/**
A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

S is empty;
S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
S has the form "VW" where V and W are properly nested strings.
For example, the string "{[()()]}" is properly nested but "([)()]" is not.

Write a function:

fun solution(S: String): Int
that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..200,000];
string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 */
fun main() {
    println(solution("{[()()]}"))
    println(solution("([)()]"))
}

private fun solution(S: String): Int {
    if (S.length.rem(2) == 1) return 0
    val stack = Stack<Char>()
    S.forEach {
        when (it) {
            '{', '[', '(' -> {
                stack.push(it)
            }
            '}', ']', ')' -> {
                if (stack.isEmpty()) return 0
                val complement = when (stack.pop()) {
                    '{' -> '}'
                    '[' -> ']'
                    '(' -> ')'
                    else -> throw IllegalArgumentException()
                }
                if (it != complement) return 0
            }
            else -> throw IllegalArgumentException()
        }
    }
    return if (stack.isEmpty()) 1 else 0
}
