import java.util.regex.Pattern
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val separator = Pattern.compile("""\s+""")

        val (leftLocations, rightLocations) = input.fold(
            emptyList<Int>() to emptyList<Int>(),
        ) { acc, line ->
            val (leftLocation, rightLocation) = line.split(separator)
            (acc.first + leftLocation.toInt()) to (acc.second + rightLocation.toInt())
        }

        return leftLocations.sorted().zip(rightLocations.sorted()) { leftLocation, rightLocation ->
            abs(leftLocation - rightLocation)
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val separator = Pattern.compile("""\s+""")
        val rightLocationCount = HashMap<Int, Int>()
        val leftLocations = ArrayList<Int>()

        input.forEach { line ->
            val (leftLocation, rightLocation) = line.split(separator)
            leftLocations.add(leftLocation.toInt())
            rightLocationCount.merge(rightLocation.toInt(), 1, Int::plus)
        }

        return leftLocations.sumOf { location ->
            val rightLocationCount = rightLocationCount[location] ?: 0
            location * rightLocationCount
        }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
