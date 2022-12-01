import org.junit.Test
import kotlin.test.assertEquals

class Day01Test {
	lateinit var solver: Day01

	@Test
	fun `given test input, fattest elf has 24000 calories`() {
		solver = Day01("1".test)
		assertEquals(24000, solver.fattestElf())
	}

	@Test
	fun `given prod input, find fattest elf`() {
		solver = Day01("1".prod)
		println(solver.fattestElf())
	}

	@Test
	fun `given test input, top three have 45000 calories`() {
		solver = Day01("1".test)
		assertEquals(45000, solver.topThreeCals())
	}

	@Test
	fun `given prod input, find top 3 elves`() {
		solver = Day01("1".prod)
		println(solver.topThreeCals())
	}
}