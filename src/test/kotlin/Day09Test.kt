import Day09.Instruction.Companion.isLegal
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day09Test {
	lateinit var solver: Day09

	@Before
	fun setUp() {
		solver = Day09("9".test)
	}

	@Test
	fun `given test input, unique tail positions is 13`() {
		assertEquals(13, solver.uniqueTailPositions())
	}

	@Test
	fun `given position, verify if it is legal`() {
		val tail = 0 to 0
		assertTrue { isLegal(1 to 0, tail) }
		assertTrue { isLegal(1 to 1, tail) }
		assertTrue { isLegal(0 to 0, tail) }
		assertFalse { isLegal(2 to 0, tail) }
	}

	@Test
	fun `solve part 1`() {
		solver = Day09("9".prod)
		println(solver.uniqueTailPositions())
	}

	@Test
	fun `given test input, 10-knot rope tail visits 1 location`() {
		assertEquals(1, solver.tenKnotRope())
	}

	@Test
	fun `given 2nd test input, 10-knot rope tail visits 36 locations`() {
		solver = Day09("9_2".test)
		assertEquals(36, solver.tenKnotRope())
	}

	@Test
	fun `given legal position, new tail does not move`() {
		val tail: Coordinate = 0 to 0
		assertEquals(tail, solver.newTail(newHead = 1 to 0, tail))
		assertEquals(tail, solver.newTail(newHead = 0 to 1, tail))
		assertEquals(tail, solver.newTail(newHead = 1 to 1, tail))
	}

	@Test
	fun `given head in same row or col, tail moves linearly`() {
		val tail: Coordinate = 0 to 0
		assertEquals(1 to 0, solver.newTail(newHead = 2 to 0, tail))
		assertEquals(0 to 1, solver.newTail(newHead = 0 to 2, tail))
	}

	@Test
	fun `given head in neither same row nor column, tail moves diagonally`() {
		val tail: Coordinate = 0 to 0
		assertEquals(1 to 1, solver.newTail(newHead = 2 to 1, tail))
		assertEquals(-1 to 1, solver.newTail(newHead = -2 to 1, tail))
		assertEquals(-1 to -1, solver.newTail(newHead = -2 to -2, tail))
		assertEquals(1 to -1, solver.newTail(newHead = 2 to -1, tail))
	}

	@Test
	fun `solve part 2`() {
		solver = Day09("9".prod)
		println(solver.tenKnotRope())
	}
}