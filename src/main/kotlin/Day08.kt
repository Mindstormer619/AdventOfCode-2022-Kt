class Day08(filename: String) {
	private val trees: List<List<Int>>

	init {
		trees = readFileAndProcess(filename) { line ->
			line.map { it.digitToInt() }
		}
	}

	/**
	 * Part 1
	 */
	fun visibleTrees(): Int {
		val directions = listOf(
			(0..trees.lastIndex) to (0..trees[0].lastIndex), // West-East
			(0..trees[0].lastIndex) to (0..trees.lastIndex), // North-South
		)

		val treesSeen = mutableSetOf<Pair<Int, Int>>()
		for (i in directions[0].first) {
			var max = -1
			for (j in directions[0].second) {
				max = updateMaximum(i, j, max, treesSeen)
			}
			max = -1
			for (j in directions[0].second.reversed()) {
				max = updateMaximum(i, j, max, treesSeen)
			}
		}

		for (j in directions[1].first) {
			var max = -1
			for (i in directions[1].second) {
				max = updateMaximum(i, j, max, treesSeen)
			}
			max = -1
			for (i in directions[1].second.reversed()) {
				max = updateMaximum(i, j, max, treesSeen)
			}
		}

		return treesSeen.size
	}

	private fun updateMaximum(
		i: Int,
		j: Int,
		max: Int,
		treesSeen: MutableSet<Pair<Int, Int>>
	): Int {
		val tree = trees[i][j]
		return if (max < tree) {
			treesSeen += i to j
			tree
		}
		else max
	}

	/**
	 * Part 2
	 */
	fun scenicScore(): Long {
		var maxScenicScore = 0L

		for (i in 1 until trees.lastIndex) {
			for (j in 1 until trees[i].lastIndex) {
				val score: Long =
					(i to j).viewingDistance('N').toLong() *
						(i to j).viewingDistance('S') *
						(i to j).viewingDistance('W') *
						(i to j).viewingDistance('E')
				if (maxScenicScore < score) maxScenicScore = score
			}
		}
		return maxScenicScore
	}

	private fun Pair<Int, Int>.viewingDistance(direction: Char): Int {
		val (x, y) = this
		val tree = trees[x][y]
		var distance = 0
		when (direction) {
			'N' -> {
				for (i in y - 1 downTo 0) {
					distance++
					if (trees[x][i] >= tree) break
				}
			}
			'S' -> {
				for (i in (y + 1)..trees.lastIndex) {
					distance++
					if (trees[x][i] >= tree) break
				}
			}
			'W' -> {
				for (i in x - 1 downTo 0) {
					distance++
					if (trees[i][y] >= tree) break
				}
			}
			'E' -> {
				for (i in (x + 1)..trees[y].lastIndex) {
					distance++
					if (trees[i][y] >= tree) break
				}
			}
		}
		return distance
	}
}