import kotlin.math.abs

class Day10(filename: String) {
	private var registerValue: Long = 1

	private val instructions: List<Instruction>

	init {
		instructions = readFileAndProcess(filename) {
			when {
				it.startsWith("addx") -> Add(it.split(' ')[1].toInt())
				it == "noop" -> Noop
				else -> throw NotImplementedError()
			}
		}
	}

	fun signalStrengthSum(): Long {
		var result = 0L

		var cycleCount = 0
		for (instruction in instructions) {
			for (ticks in 1..instruction.cyclesConsumed) {
				cycleCount++
				if (isInteresting(cycleCount)) {
					result += cycleCount * registerValue
				}
			}
			registerValue = instruction.process(registerValue)
		}

		return result
	}

	private fun isInteresting(cycleCount: Int) = (cycleCount - 20) mod 40 == 0

	fun crt(): String {
		val output = StringBuilder()
		var crtPixel = 0

		for (instruction in instructions) {
			for (tick in 1..instruction.cyclesConsumed) {
				output.append(
					if (inRange(crtPixel)) '█' else ' '
				)
				crtPixel++
				if (crtPixel == 40) {
					output.append('\n')
					crtPixel = 0
				}
			}
			registerValue = instruction.process(registerValue)
		}

		output.deleteAt(output.lastIndex) // remove last \n
		return output.toString()
	}

	private fun inRange(crtPixel: Int) = abs(registerValue - crtPixel) <= 1


	private sealed class Instruction(val cyclesConsumed: Int) {
		abstract fun process(oldRegisterValue: Long): Long
	}

	private class Add(val value: Int) : Instruction(2) {
		override fun process(oldRegisterValue: Long) = oldRegisterValue + value
	}

	private object Noop : Instruction(1) {
		override fun process(oldRegisterValue: Long) = oldRegisterValue
	}
}
