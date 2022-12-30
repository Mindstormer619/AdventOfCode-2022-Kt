import Day13.PacketList
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue
import Day13.Packet.Companion as Packet

class Day13Test {
	lateinit var solver: Day13

	@Before
	fun setUp() {
		solver = Day13("13".test)
	}

	@Test
	fun `given test input, sum of correct order indices is 13`() {
		assertEquals(13, solver.correctIndicesSum())
	}

	@Test
	fun `given packet string, we can parse it correctly`() {
		val packetString = "[[1],[2,3,4]]"
		val result = Packet.parse(packetString)
		assertIs<PacketList>(result)
		val firstElementInPacket = result[0]
		assertIs<PacketList>(firstElementInPacket)
		val firstElementInList = firstElementInPacket[0]
		assertIs<Day13.PacketInt>(firstElementInList)
		assertEquals(1, firstElementInList())
	}

	@Test
	fun `packet parsing test 2`() {
		val result = Packet.parse("[[[]]]")
		assertIs<PacketList>(result)
		assertIs<PacketList>(result[0])
		assertIs<PacketList>((result[0] as PacketList)[0])
	}

	@Test
	fun `check correctly ordered pairs`() {
		val correctPairs = listOf(
			"[1,1,3,1,1]" to "[1,1,5,1,1]",
			"[[1],[2,3,4]]" to "[[1],4]",
			"[]" to "[3]",
			"[[4,4],4,4]" to "[[4,4],4,4,4]"
		)

		for ((p1, p2) in correctPairs) {
			assertTrue { Packet.parse(p1).compare(Packet.parse(p2)) < 0 }
		}
	}

	@Test
	fun `check wrongly ordered pairs`() {
		val wrongPairs = listOf(
			"[9]" to "[[8,7,6]]",
			"[[[]]]" to "[[]]",
		)

		for ((p1, p2) in wrongPairs) {
			assertTrue { Packet.parse(p1).compare(Packet.parse(p2)) > 0 }
		}
	}

	@Test
	fun `solve part 1`() {
		solver = Day13("13".prod)
		println(solver.correctIndicesSum())
	}

	@Test
	fun `given test input, decoder key is 140`() {
		assertEquals(140, solver.decoderKey())
	}

	@Test
	fun `solve part 2`() {
		solver = Day13("13".prod)
		println(solver.decoderKey())
	}
}