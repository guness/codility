package exam2

fun main() {
    println(findWord(arrayOf("P>E", "E>R", "R>U")))
    println(findWord(arrayOf("I>N","A>I","P>A","S>P")))
    println(findWord(arrayOf("U>N", "G>A", "R>Y", "H>U", "N>G", "A>R")))
    println(findWord(arrayOf("I>F", "W>I", "S>W", "F>T")))
    println(findWord(arrayOf("R>T", "A>L", "P>O", "O>R", "G>A", "T>U", "U>G")))
    println(findWord(arrayOf("W>I", "R>L", "T>Z", "Z>E", "S>W", "E>R", "L>A", "A>N", "N>D", "I>T")))
}

fun findWord(wordArray: Array<String>): String {
    val string = StringBuilder()
    val map = mutableMapOf<Char, Char>()
    val initials = mutableListOf<Char>()

    wordArray.forEach {
        initials.add(it.first())
        map[it.first()] = it[2]
    }
    initials.removeAll(map.values.toList())

    var tmp: Char? = initials.first()

    while (map[tmp] != null) {
        string.append(tmp)
        tmp = map[tmp]
    }
    string.append(tmp)

    return string.toString()
}

