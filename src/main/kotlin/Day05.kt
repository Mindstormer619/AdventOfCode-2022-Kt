import java.io.File
import java.util.*

class Day05(filename: String) {
	val stacks: MutableList<Stack<Char>> = mutableListOf()
	private val moves: List<Move>

	init {
		File(filename).useLines {
			val x = it.takeWhile { line -> !line.startsWith(" 1") }
				.toList()

			var j = 1
			while (j < x[0].length) {
				val currentStack = Stack<Char>()
				for (i in (x.lastIndex downTo 0)) {
					if (x[i][j] != ' ') currentStack += x[i][j]
				}
				stacks.add(currentStack)
				j += 4
			}
		}

		moves = File(filename).useLines { lines ->
			val x = lines.filter { it.startsWith("move") }
				.map {
					val (_, count, from, to) = it.split("move ", " from ", " to ")
					Move(count.toInt(), from.toInt() - 1, to.toInt() - 1)
				}.toList()
			x
		}
	}

	fun stackTops(): String {
		for (move in moves) {
			repeat(move.count) {
				stacks[move.to].push(stacks[move.from].pop())
			}
		}

		val stringBuilder = StringBuilder()
		stacks.forEach {stringBuilder.append(it.peek())}
		return stringBuilder.toString()
	}

	fun multiStack(): String {
		val tempStack = Stack<Char>()
		for (move in moves) {
			repeat(move.count) {
				tempStack.push(stacks[move.from].pop())
			}
			while (tempStack.isNotEmpty()) {
				stacks[move.to].push(tempStack.pop())
			}
		}

		val stringBuilder = StringBuilder()
		stacks.forEach {stringBuilder.append(it.peek())}
		return stringBuilder.toString()
	}
}

private data class Move(
	val count: Int,
	val from: Int,
	val to: Int,
)