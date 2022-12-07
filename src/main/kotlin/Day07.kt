class Day07(filename: String) {
	private val root = Dir("/")

	init {
		var currentDir: Dir? = root
		readFileAndProcess(filename) { line ->
			when {
				line == "\$ ls" -> currentDir?.sizeOfFiles = 0
				line == "\$ cd /" -> currentDir = root
				line == "\$ cd .." -> currentDir = currentDir?.parent
				line.startsWith("\$ cd ") -> {
					val destination = currentDir?.children?.get(line.split(' ')[2]) ?: root
					currentDir = destination
				}
				line.startsWith("dir ") -> {
					val name = line.split(' ')[1]
					if (currentDir?.children?.containsKey(name) == false){
						val newDir = Dir(name)
						newDir.parent = currentDir
						currentDir?.children?.set(name, newDir)
					}
				}
				else -> { //<size> <fileName>
					val (size, _) = line.split(' ')
					currentDir?.sizeOfFiles = (currentDir?.sizeOfFiles ?: 0) + size.toLong()
				}
			}
		}
		root.updateTotalSize()
	}

	fun sumOf100kDirs() = collectDirs().filter { it.totalSize <= 100_000 }.sumOf { it.totalSize }

	fun smallestCleanupDir(): Long {
		val totalDiskSpace = 70_000_000L; val target = 30_000_000L
		val currentFreeSpace = totalDiskSpace - root.totalSize
		val toClean = target - currentFreeSpace

		return collectDirs()
			.filter { it.totalSize >= toClean }
			.minBy { it.totalSize }.totalSize
	}

	private fun collectDirs(currentDir: Dir = root): Set<Dir> {
		val result = mutableSetOf<Dir>()
		result += currentDir
		for (dir in currentDir.children.values) {
			result += dir
			result += collectDirs(dir)
		}
		return result
	}
}

private class Dir(
	val name: String,
	var parent: Dir? = null,
	var sizeOfFiles: Long = 0,
	var totalSize: Long = 0,
	val children: MutableMap<String, Dir> = mutableMapOf()
) {
	fun updateTotalSize(): Long {
		if (totalSize > 0) return totalSize
		totalSize = sizeOfFiles + children.values.sumOf { it.updateTotalSize() }
		return totalSize
	}
}