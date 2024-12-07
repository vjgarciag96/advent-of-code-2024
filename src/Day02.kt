private val decreasing = setOf(1, 2, 3)
private val increasing = setOf(-1, -2, -3)

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            line.split(' ').map { level -> level.toInt() }
        }.count { levels -> Report(levels).isSafe() }
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            line.split(' ').map { level -> level.toInt() }
        }.count { levels ->
            levels
                .indices
                .any { index -> Report(levels.slice(levels.indices - index)).isSafe() }
        }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

private data class Report(
    val levels: List<Int>,
)

private fun Report.isSafe(): Boolean {
    if (levels.size < 2) {
        return true
    }

    val pairs = levels
        .asSequence()
        .windowed(2)

    return pairs.all { (first, second) -> first - second in decreasing } ||
            pairs.all { (first, second) -> first - second in increasing }
}
