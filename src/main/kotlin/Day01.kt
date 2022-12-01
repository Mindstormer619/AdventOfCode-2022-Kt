import java.io.File

class Day01(filename: String) {
	private val elvishWeights = mutableListOf<Long>()

	init {
		var currentElfWeight = 0L
		File(filename).useLines {line -> line.forEach {
			if (it == "") {
				elvishWeights += currentElfWeight
				currentElfWeight = 0
			}
			else {
				currentElfWeight += it.toLong()
			}
		}}
		elvishWeights += currentElfWeight
	}

	fun fattestElf(): Long {
		return elvishWeights.max().toLong()
	}

	fun topThreeCals(): Long {
		val top3 = mutableListOf<Long>(0, 0, 0)
		for (weight in elvishWeights) {
			top3 += weight
			top3.sort()
			top3.removeAt(0)
		}

		return top3.sum()
	}
}