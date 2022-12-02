import RpsRound.Companion.createRps
import kotlin.RuntimeException

class Day02(filename: String) {

	private val rounds: List<RpsRound>

	init {
		rounds = readFileAndProcess(filename) { line ->
			createRps(Pair(line[0], line[2]))
		}
	}

	fun followStratGuide() = rounds.sumOf { it.score1() }

	fun followNewStrat() = rounds.sumOf { it.score2() }
}

private data class RpsRound(
	val player2: Int,
	val player1: Int
) {
	companion object {
		const val ROCK = 0
		const val PAPER = 1
		const val SCISSOR = 2

		const val LOSE = 0
		const val DRAW = 1
		const val WIN = 2

		fun createRps(chars: Pair<Char, Char>): RpsRound {
			return RpsRound(
				when(chars.first) {
					'A' -> ROCK
					'B' -> PAPER
					'C' -> SCISSOR
					else -> throw RuntimeException()
				},
				when(chars.second) {
					'X' -> ROCK
					'Y' -> PAPER
					'Z' -> SCISSOR
					else -> throw RuntimeException()
				}
			)
		}
	}

	fun score1() = player1 + 1 + outcomeScore(player1)
	fun score2() = secondStrategy() + 1 + outcomeScore(secondStrategy())

	fun outcomeScore(play: Int): Int {
		if (((player2 - play) mod 3) == 1) return 0
		if (player2 == play) return 3
		return 6
	}

	fun secondStrategy() = when(player1) {
		LOSE -> (player2 - 1) mod 3
		DRAW -> player2
		WIN -> (player2 + 1) mod 3
		else -> throw RuntimeException()
	}
}