import java.io.File

class Day11(filename: String) {
	private val monkeys: List<Monkey>
	private var lcm: Long
	private val monkeyRegex = Regex(
		"""Monkey (.+):
 {2}Starting items: (.*)
 {2}Operation: new = old (.) (.+)
 {2}Test: divisible by (.*)
 {4}If true: throw to monkey (.+)
 {4}If false: throw to monkey (.+)""".replace("\n", System.lineSeparator())
	)

	init {
		val newLine = System.lineSeparator()
		monkeys = File(filename).readText().split("$newLine$newLine")
			.map { monkeyRegex.matchAt(it, 0)!!.groupValues }
			.map { groups ->
				Monkey(
					groups[2].split(", ").map { it.toLong() }.toMutableList(),
					if (groups[3] == "+") Operation.PLUS else Operation.TIMES,
					if (groups[4] == "old") -1 else groups[4].toLong(),
					groups[5].toLong(),
					Pair(groups[6].toInt(), groups[7].toInt())
				)
			}

		lcm = monkeys.map { it.divisor }.reduce { acc, l -> acc * l }
	}

	fun monkeyBusiness(): Long {
		repeat(20) {
			runEasyRound()
		}
		val sortedMonkeys = monkeys.sortedBy { monkey -> monkey.inspected }
		return sortedMonkeys.last().inspected * sortedMonkeys[sortedMonkeys.lastIndex - 1].inspected
	}

	private fun runEasyRound() {
		for (monkey in monkeys) {
			for (item in monkey.items) {
				val worryLevel = monkey.applyOperation(item) / 3
				monkey.inspected++
				monkeys[monkey.targetMonkey(worryLevel)].items += worryLevel
			}
			monkey.items.clear()
		}
	}

	fun seriousBusiness(): Long {
		repeat(10_000) {
			runHardRound()
		}
		val sortedMonkeys = monkeys.sortedBy { monkey -> monkey.inspected }
		return sortedMonkeys.last().inspected * sortedMonkeys[sortedMonkeys.lastIndex - 1].inspected
	}

	private fun runHardRound() {
		for (monkey in monkeys) {
			for (item in monkey.items) {
				val worryLevel = monkey.applyOperation(item) mod lcm
				monkey.inspected++
				monkeys[monkey.targetMonkey(worryLevel)].items += worryLevel
			}
			monkey.items.clear()
		}
	}


}

private data class Monkey(
	val items: MutableList<Long>,
	val operation: Operation,
	val operand: Long, // if -1, use the operand twice
	val divisor: Long,
	val throwTo: Pair<Int, Int>,
	var inspected: Long = 0
) {
	fun applyOperation(item: Long) = when(operation) {
		Operation.TIMES -> if (operand == -1L) item * item else item * operand
		Operation.PLUS -> if (operand == -1L) item + item else item + operand
	}

	fun targetMonkey(item: Long) =
		if (item mod divisor == 0L) throwTo.first else throwTo.second
}

private enum class Operation {
	PLUS, TIMES
}