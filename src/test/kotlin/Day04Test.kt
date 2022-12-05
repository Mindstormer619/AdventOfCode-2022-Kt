import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day04Test {
	lateinit var solver: Day04

	@Test
	fun `given test input, there are 2 full overlaps`() {
		solver = Day04("4".test)
		assertEquals(2, solver.fullOverlaps())
	}

	@Test
	fun `check if split function works`() {
		assertEquals("38", "22-41,38-33".split(",", "-")[2])
	}

	@Test
	fun `check IntRange#contains works`() {
		assertTrue { (2..8) contains (3..7) }
		assertTrue { (4..6) contains 6..6 }
		assertFalse { (2..3) contains (3..5) }
	}

	@Test
	fun `part 1 for prod`() {
		solver = Day04("4".prod)
		println(solver.fullOverlaps())
	}

	@Test
	fun `given test input, 4 partial overlaps`() {
		solver = Day04("4".test)
		assertEquals(4, solver.partialOverlaps())
	}

	@Test
	fun `check IntRange#overlap works`() {
		assertTrue { (5..7) overlaps (7..9) }
		assertTrue { (2..8) overlaps (3..7) }
		assertFalse { (2..4) overlaps (6..8) }
	}

	@Test
	fun `solve part 2`() {
		solver = Day04("4".prod)
		println(solver.partialOverlaps())
	}
}