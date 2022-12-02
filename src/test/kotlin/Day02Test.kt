import org.junit.Test
import kotlin.test.assertEquals

class Day02Test {
	lateinit var solver: Day02

	@Test
	fun `given test input, strategy guide gives 15`() {
		solver = Day02("2".test)
		assertEquals(15, solver.followStratGuide())
	}

	@Test
	fun `given prod input, print strat guide`() {
		solver = Day02("2".prod)
		println(solver.followStratGuide())
	}

	@Test
	fun `given test input, new strat gives 12`() {
		solver = Day02("2".test)
		assertEquals(12, solver.followNewStrat())
	}

	@Test
	fun `given prod input, prints new strat`() {
		solver = Day02("2".prod)
		println(solver.followNewStrat())
	}
}