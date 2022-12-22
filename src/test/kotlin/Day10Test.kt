import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {
	lateinit var solver : Day10

	@Before
	fun setUp() {
		solver = Day10("10".test)
	}

	@Test
	fun `given test input, sum of signal strengths is 13140`() {
		assertEquals(13140, solver.signalStrengthSum())
	}

	@Test
	fun `solve part 1`() {
		solver = Day10("10".prod)
		println(solver.signalStrengthSum())
	}

	@Test
	fun `given test input, output contains given rows`() {
		val expectedOutput = """
			██  ██  ██  ██  ██  ██  ██  ██  ██  ██  
			███   ███   ███   ███   ███   ███   ███ 
			████    ████    ████    ████    ████    
			█████     █████     █████     █████     
			██████      ██████      ██████      ████
			███████       ███████       ███████     
			""".trimIndent()

		assertEquals(
			expectedOutput,
			solver.crt()
		)
	}

	@Test
	fun `solve part 2`() {
		solver = Day10("10".prod)
		println(solver.crt())
	}
}