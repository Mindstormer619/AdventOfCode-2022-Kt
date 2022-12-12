import java.io.File

fun <T> readFileAndProcess(filePath: String, transform: (String) -> T): List<T> =
	File(filePath).useLines { sequence -> sequence.map(transform).toList() }

infix fun Int.mod(other: Int) = Math.floorMod(this, other)
infix fun Long.mod(other: Long) = Math.floorMod(this, other)

infix fun IntRange.contains(other: IntRange) =
	this.first <= other.first && this.last >= other.last

infix fun IntRange.overlaps(other: IntRange) =
	this.last >= other.first && other.last >= this.first