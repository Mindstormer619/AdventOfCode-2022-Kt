import kotlin.math.abs

typealias Coordinate = Pair<Int, Int>

class Day09(filename: String) {
	private val instructions: List<Instruction>

	init {
		instructions = readFileAndProcess(filename) {
			val (direction, steps) = it.split(' ')
			when (direction) {
				"R" -> R(steps.toInt())
				"U" -> U(steps.toInt())
				"L" -> L(steps.toInt())
				"D" -> D(steps.toInt())

				else -> throw NotImplementedError("tf is $direction")
			}
		}
	}

	fun uniqueTailPositions() = findTailPositions(MutableList(2) {0 to 0})

	fun tenKnotRope() = findTailPositions(MutableList(10) { 0 to 0 })

	private fun findTailPositions(rope: MutableList<Coordinate>): Int {
		val result = mutableSetOf(0 to 0)

		for (instruction in instructions) {
			nextStep@ for (step in 1..instruction.steps) {
				// move first node
				rope[0] = instruction.move(rope[0])

				// propagate move down the rope
				for (i in 0 until rope.lastIndex) {
					val newTail = newTail(rope[i], rope[i + 1])
					if (newTail == rope[i + 1]) continue@nextStep
					rope[i + 1] = newTail
					if (i + 1 == rope.lastIndex) result += newTail
				}
			}
		}

		return result.size
	}

	fun newTail(newHead: Coordinate, tail: Coordinate): Coordinate {
		return when {
			Instruction.isLegal(newHead, tail) -> tail

			newHead.first == tail.first -> {
				tail.first to (if (newHead.second > tail.second) tail.second + 1 else tail.second - 1)
			}

			newHead.second == tail.second -> {
				(if (newHead.first > tail.first) tail.first + 1 else tail.first - 1) to tail.second
			}

			else -> {
				Pair(
					if (newHead.first > tail.first) tail.first + 1 else tail.first - 1,
					if (newHead.second > tail.second) tail.second + 1 else tail.second - 1
				)
			}
		}
	}


	sealed class Instruction(val steps: Int) {
		abstract fun move(coordinate: Coordinate): Coordinate

		companion object {
			fun isLegal(head: Coordinate, tail: Coordinate): Boolean {
				return abs(head.first - tail.first) <= 1 && abs(head.second - tail.second) <= 1
			}
		}
	}

	class R(steps: Int) : Instruction(steps) {
		override fun move(coordinate: Coordinate) = coordinate.first + 1 to coordinate.second
	}

	class L(steps: Int) : Instruction(steps) {
		override fun move(coordinate: Coordinate) = coordinate.first - 1 to coordinate.second
	}

	class U(steps: Int) : Instruction(steps) {
		override fun move(coordinate: Coordinate) = coordinate.first to coordinate.second + 1
	}

	class D(steps: Int) : Instruction(steps) {
		override fun move(coordinate: Coordinate) = coordinate.first to coordinate.second - 1
	}
}