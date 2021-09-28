class MatrixBuilder {

}

fun buildMatrix(lambda: MatrixBuilder.() -> Unit): MatrixBuilder {
	val builder = MatrixBuilder()

	builder.lambda()

	return builder
}

fun test() {
	buildMatrix {
	}
}

class Matrix<T>(
	private val rows: Int,
	private val columns: Int
) {

	val elements: MutableList<T> = mutableListOf()

}