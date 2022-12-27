import kotlin.math.min

class Day12(filename: String) {
	private lateinit var start: Coordinate
	private lateinit var end: Coordinate

	private val graph = mutableMapOf<Coordinate, Set<Coordinate>>()

	private val elevationMap: List<String>

	init {
		var rowCount = 0
		elevationMap = readFileAndProcess(filename) {
			val s = it.indexOf('S')
			val e = it.indexOf('E')
			if (s != -1) start = rowCount to s
			if (e != -1) end = rowCount to e
			rowCount++
			it
		}

		for (i in elevationMap.indices) {
			for (j in elevationMap[i].indices) {
				graph[i to j] = generateValidNeighbors(i to j)
			}
		}
	}

	/**
	 * Part 1
	 */
	fun shortestPath(): Int {
		val visitedNodes = mutableSetOf<Coordinate>()
		val distances = mutableMapOf(*graph.keys.map { it to Int.MAX_VALUE }.toTypedArray())

		distances[start] = 0

		while (true) {
			val (coordinate, distance) = distances
				.filter { it.key !in visitedNodes }
				.minWith { a, b -> a.value - b.value }
			if (coordinate == end) return distance
			for (neighbor in graph.getValue(coordinate)) {
				if (neighbor !in visitedNodes) {
					distances[neighbor] = min(distance + 1, distances.getValue(neighbor))
				}
			}
			visitedNodes += coordinate
		}
	}

	private fun generateValidNeighbors(point: Coordinate): Set<Coordinate> {
		val possibleNeighbors = listOf(
			point.first to point.second - 1,
			point.first to point.second + 1,
			point.first - 1 to point.second,
			point.first + 1 to point.second
		)
		return possibleNeighbors
			.filter { it.first in elevationMap.indices && it.second in elevationMap[0].indices }
			.filter { isValidJump(elevationMap.at(point), elevationMap.at(it)) }
			.toSet()
	}

	private fun isValidJump(from: Char, to: Char): Boolean {
		val destination = getElevationMarker(to)
		val source = getElevationMarker(from)
		// destination can be at most 1 bigger than the source point
		return destination - source <= 1
	}

	private fun getElevationMarker(point: Char) = when (point) {
		'S' -> 'a'
		'E' -> 'z'
		else -> point
	}

	private fun List<String>.at(point: Coordinate) = this[point.first][point.second]

	/**
	 * Part 2
	 */
	fun anyShortestPath(): Int {
		val graph = mutableMapOf<Coordinate, Set<Coordinate>>()
		for (i in elevationMap.indices) {
			for (j in elevationMap[i].indices) {
				graph[i to j] = validSourceNeighbors(i to j)
			}
		}

		val visitedNodes = mutableSetOf<Coordinate>()
		val distances = mutableMapOf(*graph.keys.map { it to 100_000 }.toTypedArray())

		distances[end] = 0

		while (visitedNodes.size < graph.keys.size) {
			val (coordinate, distance) = distances
				.filter { it.key !in visitedNodes }
				.minWith { a, b -> a.value - b.value }
			for (neighbor in graph.getValue(coordinate)) {
				if (neighbor !in visitedNodes) {
					distances[neighbor] = min(distance + 1, distances.getValue(neighbor))
				}
			}
			visitedNodes += coordinate
		}
		return distances.getValue(
			visitedNodes
				.filter { getElevationMarker(elevationMap.at(it)) == 'a' }
				.minBy { distances.getValue(it) }
		)
	}

	private fun validSourceNeighbors(point: Coordinate): Set<Coordinate> {
		val possibleNeighbors = listOf(
			point.first to point.second - 1,
			point.first to point.second + 1,
			point.first - 1 to point.second,
			point.first + 1 to point.second
		)
		return possibleNeighbors
			.filter { it.first in elevationMap.indices && it.second in elevationMap[0].indices }
			.filter { isValidJump(elevationMap.at(it), elevationMap.at(point)) }
			.toSet()
	}
}