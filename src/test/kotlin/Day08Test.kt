import org.junit.Test
import kotlin.test.assertEquals

class Day08Test {
	lateinit var solver: Day08

	@Test
	fun `given test input, visible trees is 21`() {
		solver = Day08("8".test)
		assertEquals(21, solver.visibleTrees())
	}

	@Test
	fun `solve part 1`() {
		solver = Day08("8".prod)
		println(solver.visibleTrees())
	}

	@Test
	fun `given test input, scenic score is 8`() {
		solver = Day08("8".test)
		assertEquals(8, solver.scenicScore())
	}

	@Test
	fun `solve part 2`() {
		solver = Day08("8".prod)
		println(solver.scenicScore())
	}
}