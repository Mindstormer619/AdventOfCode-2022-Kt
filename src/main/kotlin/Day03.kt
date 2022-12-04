class Day03(filename: String) {
	private val rucksacks: List<Rucksack>

	init {
		rucksacks = readFileAndProcess(filename) {
			Rucksack(
				compartment1 = it.slice(0 until it.length/2),
				compartment2 = it.slice(it.length/2 until it.length)
			)
		}
	}

	fun sumOfPriorities() = rucksacks
		.map { Pair(HashSet(it.compartment1.toList()), HashSet(it.compartment2.toList())) }
		.map { (it.first.intersect(it.second)).first() }
		.sumOf { it.priority() }

	fun elfGroups(): Int {
		var result = 0
		var i = 0
		while (i+2 < rucksacks.size) {
			val (elf1, elf2, elf3) = Triple(
				rucksacks[i].contents(),
				rucksacks[i + 1].contents(),
				rucksacks[i + 2].contents()
			)

			val common = elf1 intersect elf2 intersect elf3
			result += common.first().priority()
			i += 3
		}
		return result
	}

	companion object {
		private fun Char.priority() : Int {
			return when(this) {
				in 'a'..'z' -> this.code - 'a'.code + 1
				in 'A'..'Z' -> this.code - 'A'.code + 27
				else -> throw NotImplementedError("tf is $this")
			}
		}
	}
}

private data class Rucksack(
	val compartment1: String,
	val compartment2: String
) {
	fun contents() = compartment1.toSet() union compartment2.toSet()
}
