import org.junit.Test
import kotlin.test.assertEquals

class Day03Test {
	lateinit var solver: Day03

	@Test
	fun `given test input, sum of priorities is 157`() {
		solver = Day03("3".test)
		assertEquals(157, solver.sumOfPriorities())
	}

	@Test
	fun `we can use slice to divide a string into two halves`() {
		val s = "abcdef"
		assertEquals("abc", s.slice(0 until s.length/2))
		assertEquals("def", s.slice(s.length/2 until s.length))
	}

	@Test
	fun `solve part 1`() {
		solver = Day03("3".prod)
		println(solver.sumOfPriorities())
	}

	@Test
	fun `given test input, elfGroups gives 70`() {
		solver = Day03("3".test)
		assertEquals(70, solver.elfGroups())
	}

	@Test
	fun `solve part 2`() {
		solver = Day03("3".prod)
		println(solver.elfGroups())
	}
}