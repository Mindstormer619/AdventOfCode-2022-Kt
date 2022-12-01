val String.prod: String
	get() = "src/main/resources/$this.txt"
val String.test: String
	get() = "src/test/resources/$this.txt"