import org.junit.Test
import kotlin.test.assertEquals

class ExampleTest {
	@Test
	fun `add 1 to 1`() {
		assertEquals(2, 1+1);
	}

	@Test
	fun `mod function works`() {
		assertEquals(1, -2 mod 3)
	}
}