import java.util.*

class Day13(filename: String) {
	private val packetPairs: List<Pair<Packet, Packet>>

	init {
		var firstPacket: Packet? = null
		var secondPacket: Packet
		val runningList = mutableListOf<Pair<Packet, Packet>>()
		var addingFirstPacket = true
		readFileAndProcess(filename) {
			when {
				it.isBlank() -> Unit
				else -> {
					if (addingFirstPacket) {
						firstPacket = Packet.parse(it)
						addingFirstPacket = false
					} else {
						secondPacket = Packet.parse(it)
						addingFirstPacket = true
						runningList += firstPacket!! to secondPacket
					}
				}
			}
		}
		packetPairs = runningList
	}

	/**
	 * Part 1
	 */
	fun correctIndicesSum() = packetPairs
		.mapIndexed { i, (a, b) -> i + 1 to a.compare(b) }
		.filter { (_, comparison) -> comparison < 0 }
		.sumOf { (index, _) -> index }

	/**
	 * Part 2
	 */
	fun decoderKey(): Int {
		val fullPacketList = mutableListOf<Packet>()
		for ((a, b) in packetPairs) {
			fullPacketList += a
			fullPacketList += b
		}
		val divider1 = Packet.parse("[[2]]")
		fullPacketList += divider1
		val divider2 = Packet.parse("[[6]]")
		fullPacketList += divider2

		fullPacketList.sortWith { a, b -> a.compare(b) }

		return (fullPacketList.indexOf(divider1) + 1) * (fullPacketList.indexOf(divider2) + 1)
	}

	sealed class Packet {
		companion object {
			fun parse(packetString: String): PacketList {
				var areWeParsingANumber = false
				var num = 0
				val openLists: Stack<PacketList> = Stack()

				for (c in packetString) {
					when {
						c == '[' -> {
							openLists += PacketList(mutableListOf())
						}
						c.isDigit() -> {
							areWeParsingANumber = true
							num *= 10
							num += c.digitToInt()
						}
						c == ',' -> {
							if (areWeParsingANumber) {
								openLists.peek() += PacketInt(num)
								num = 0
								areWeParsingANumber = false
							}
						}
						c == ']' -> {
							if (areWeParsingANumber) {
								openLists.peek() += PacketInt(num)
								num = 0
								areWeParsingANumber = false
							}
							val completedList = openLists.pop()
							if (openLists.empty()) return completedList
							else openLists.peek() += completedList
						}
					}
				}
				throw IllegalStateException("PacketString $packetString is unparseable!")
			}
		}

		abstract fun compare(other: Packet): Int
	}

	class PacketList(private val components: MutableList<Packet>) : Packet() {
		operator fun get(i: Int) = components[i]
		operator fun plusAssign(p: Packet) {
			components += p
		}

		override fun compare(other: Packet): Int {
			when (other) {
				is PacketList -> {
					for ((element1, element2) in components.zip(other.components)) {
						val compare = element1.compare(element2)
						if (compare == 0) continue
						else return compare
					}
					return this.components.size - other.components.size
				}
				is PacketInt -> return compare(PacketList(mutableListOf(other)))
			}
		}

		override fun toString(): String {
			return "List: $components"
		}
	}

	class PacketInt(private val value: Int) : Packet() {
		operator fun invoke(): Int {
			return value
		}

		override fun compare(other: Packet): Int {
			return when (other) {
				is PacketInt -> this.value - other.value
				is PacketList -> PacketList(mutableListOf(this)).compare(other)
			}
		}

		override fun toString(): String {
			return "Int: $value"
		}
	}
}