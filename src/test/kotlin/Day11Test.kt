import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {
	lateinit var solver: Day11

	@Test
	fun `given test input, monkey business is 10605`() {
		solver = Day11("11".test)
		assertEquals(10605L, solver.monkeyBusiness())
	}

	@Test
	fun `solve part 1`() {
		solver = Day11("11".prod)
		println(solver.monkeyBusiness())
	}

	@Test
	fun `given test input, serious business is 2713310158`() {
		solver = Day11("11".test)
		assertEquals(2713310158, solver.seriousBusiness())
	}

	@Test
	fun `solve part 2`() {
		solver = Day11("11".prod)
		println(solver.seriousBusiness())
	}
}