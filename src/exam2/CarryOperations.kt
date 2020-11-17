package exam2

import kotlin.math.log10
import kotlin.math.max

fun main() {
    println(numberOfCarryOperations(123, 456)) // 0
    println(numberOfCarryOperations(555, 555)) // 3
    println(numberOfCarryOperations(900, 11)) // 0
    println(numberOfCarryOperations(145, 55)) // 2
    println(numberOfCarryOperations(0, 0)) // 0
    println(numberOfCarryOperations(1, 99999)) // 5
    println(numberOfCarryOperations(999045, 1055)) // 5
    println(numberOfCarryOperations(101, 809)) // 1
    println(numberOfCarryOperations(189, 209)) // 1
}


fun numberOfCarryOperations(first: Int, second: Int): Int {
    var result = 0
    val max = max(first, second)
    val digits = maxOf(1, log10(max.toDouble()).toInt() + 1)

    val firstArray = IntArray(digits) { 0 }
    val secondArray = IntArray(digits) { 0 }

    first.toString().reversed().forEachIndexed { index, char ->
        firstArray[index] = "$char".toInt()
    }

    second.toString().reversed().forEachIndexed { index, char ->
        secondArray[index] = "$char".toInt()
    }

    var tmp = 0
    for (i in 0 until digits) {
        val sum = firstArray[i] + secondArray[i] + tmp
        tmp = if (sum >= 10) {
            result++
            sum / 10
        } else {
            0
        }
    }

    return result
}
