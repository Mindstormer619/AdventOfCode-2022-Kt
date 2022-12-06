import org.junit.Test
import kotlin.test.assertEquals

class Day05Test {
	lateinit var solver: Day05

	@Test
	fun `input stacks are parsed successfully`() {
		solver = Day05("5".test)

		assertEquals(3, solver.stacks[1].size)
		println(solver.stacks)
	}

	@Test
	fun `given test input, stack tops read CMZ`() {
		solver = Day05("5".test)
		assertEquals("CMZ", solver.stackTops())
	}

	@Test
	fun `solve part 1`() {
		solver = Day05("5".prod)
		println(solver.stackTops())
	}

	@Test
	fun `given test input, multi stack approach reads MCD`() {
		solver = Day05("5".test)
		assertEquals("MCD", solver.multiStack())
	}

	@Test
	fun `solve part 2`() {
		solver = Day05("5".prod)
		println(solver.multiStack())
	}
}