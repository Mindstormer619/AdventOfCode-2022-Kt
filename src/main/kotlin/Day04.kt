class Day04(filename: String) {
	private val ranges: List<Pair<IntRange, IntRange>>

	init {
		ranges = readFileAndProcess(filename) { line ->
			val (e11, e12, e21, e22) = line.split(",", "-").map(String::toInt)
			Pair(e11..e12, e21..e22)
		}
	}

	fun fullOverlaps() =
		ranges.count { (r1, r2) ->
			r1 contains r2 || r2 contains r1
		}

	fun partialOverlaps(): Int {
		return ranges.count { (r1, r2) ->
			r1 overlaps r2
		}
	}
}