class Day06(filename: String) {
	private val data: String

	init {
		data = readFileAndProcess(filename) {
			it
		}[0]
	}

	fun packetMarker(): Int {
		return findUnique(4)
	}

	private fun findUnique(packetLength: Int): Int {
		nextWindow@ for (i in (0..data.length - packetLength)) {
			val window = data.toCharArray(startIndex = i, endIndex = i + packetLength).sorted()
			for (j in (0 until window.size - 1)) {
				if (window[j] == window[j + 1]) continue@nextWindow
			}
			return i + packetLength
		}

		throw RuntimeException("We're never supposed to get here!")
	}

	fun messageMarker(): Int {
		return findUnique(14)
	}
}