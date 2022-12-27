import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {
	lateinit var solver: Day12

	@Before
	fun setUp() {
		solver = Day12("12".test)
	}

	@Test
	fun `given test input, shortest path is 31`() {
		assertEquals(31, solver.shortestPath())
	}

	@Test
	fun `solve part 1`() {
		solver = Day12("12".prod)
		println(solver.shortestPath())
	}

	@Test
	fun `given test input, shortest path from any starting point is 29`() {
		assertEquals(29, solver.anyShortestPath())
	}

	@Test
	fun `solve part 2`() {
		solver = Day12("12".prod)
		println(solver.anyShortestPath())
	}
}