import org.junit.Test
import kotlin.test.assertEquals

class Day07Test {
	lateinit var solver: Day07

	@Test
	fun `given test input, dirs under 100k add up to 95437`() {
		solver = Day07("7".test)
		assertEquals(95437, solver.sumOf100kDirs())
	}

	@Test
	fun `solve part 1`() {
		solver = Day07("7".prod)
		println(solver.sumOf100kDirs())
	}

	@Test
	fun `given test input, smallest cleanup dir is 24933642`() {
		solver = Day07("7".test)
		assertEquals(24933642, solver.smallestCleanupDir())
	}

	@Test
	fun `solve part 2`() {
		solver = Day07("7".prod)
		println(solver.smallestCleanupDir())
	}
}