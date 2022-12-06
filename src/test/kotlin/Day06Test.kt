import org.junit.Test
import kotlin.test.assertEquals

class Day06Test {
	lateinit var solver: Day06

	@Test
	fun `given test input, packet start marker is at 7`() {
		solver = Day06("6".test)
		assertEquals(7, solver.packetMarker())
	}

	@Test
	fun `solve part 1`() {
		solver = Day06("6".prod)
		println(solver.packetMarker())
	}

	@Test
	fun `given test input, message start marker is at 19`() {
		solver = Day06("6".test)
		assertEquals(19, solver.messageMarker())
	}

	@Test
	fun `solve part 2`() {
		solver = Day06("6".prod)
		println(solver.messageMarker())
	}
}