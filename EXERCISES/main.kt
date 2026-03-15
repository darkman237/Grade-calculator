fun <T : Comparable<T>> maxOf(list: List<T>): T? {
    return list.fold(null as T?) { acc, item ->
        if (acc == null || item > acc) item else acc
    }
}

fun main() {
    println(maxOf(listOf(3, 7, 2, 9)))
    println(maxOf(listOf("apple", "banana", "kiwi")))
    println(maxOf(emptyList<Int>()))
}