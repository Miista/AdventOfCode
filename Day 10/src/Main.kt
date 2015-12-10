fun main(args: Array<String>) {
    println(count(111221))
}

fun count(i: Int): String = count2( i.toString() )

fun count2(n: CharSequence): String {
    val b = StringBuilder()
    var rest: CharSequence = n
    do {
        val result = count1(rest[0], rest.slice(1..rest.length-1))
        b.append( "${result.first.toString()}$rest[0]" )
        rest = result.second
    } while (rest.length > 0)
    return b.toString()
}

fun count1(c: Char, rest: CharSequence): Pair<Int, CharSequence> =
    when {
        rest.length == 0 -> Pair(1, rest)
        c == rest[0] -> {
            val slice = rest.sliceOrEmpty(1..rest.length-1)
            val co = count1(rest[0], slice)
            Pair(1 + co.first, co.second)
        }
        else -> Pair(1, rest)
    }

fun CharSequence.sliceOrEmpty(range: IntRange): CharSequence {
    return when {
        this.length == 1 && range.start > 1 -> ""
        else -> this.slice(range)
    }
}