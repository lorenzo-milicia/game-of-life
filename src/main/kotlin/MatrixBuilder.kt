class MatrixBuilder<T>(
	private val rows: Int,
	private val columns: Int
) {

	private var matrix: Matrix<T> = Matrix(rows, columns)

	fun build(): Matrix<T> {
		val product = matrix
		reset()
		return product
	}

	private fun reset() {
		matrix = Matrix(rows, columns)
	}

	fun row(rowFun: RowBuilder<T>.() -> Unit) {
		val rowBuilder = RowBuilder<T>(columns)

		rowFun(rowBuilder)

		matrix.addRow(rowBuilder.build())
	}

	fun all(element: T) {
		(0 until rows).forEach { _ -> matrix.addRow(Row(columns, element)) }
	}

	fun all(function: () -> T) {
		(0 until rows).forEach { _ -> matrix.addRow(Row(columns, function)) }
	}

	fun spaceship(n: Int, m: Int, alive: T, dead: T, horizontalFlip: Boolean = false, verticalFlip: Boolean = false) {
		val horizontalMultiplier = if (horizontalFlip) -1 else 1
		val verticalMultiplier = if (verticalFlip) -1 else 1
		matrix.changeElement(n + 0 * verticalMultiplier, m + 0 * horizontalMultiplier, dead)
		matrix.changeElement(n + 1 * verticalMultiplier, m + 0 * horizontalMultiplier, dead)
		matrix.changeElement(n + 2 * verticalMultiplier, m + 0 * horizontalMultiplier, alive)
		matrix.changeElement(n + 0 * verticalMultiplier, m + 1 * horizontalMultiplier, alive)
		matrix.changeElement(n + 1 * verticalMultiplier, m + 1 * horizontalMultiplier, dead)
		matrix.changeElement(n + 2 * verticalMultiplier, m + 1 * horizontalMultiplier, alive)
		matrix.changeElement(n + 0 * verticalMultiplier, m + 2 * horizontalMultiplier, dead)
		matrix.changeElement(n + 1 * verticalMultiplier, m + 2 * horizontalMultiplier, alive)
		matrix.changeElement(n + 2 * verticalMultiplier, m + 2 * horizontalMultiplier, alive)
	}

	fun gospersCannon(n: Int, m: Int, alive: T, dead: T, horizontalFlip: Boolean = false, verticalFlip: Boolean = false) {
		val horizontalMultiplier = if (horizontalFlip) -1 else 1
		val verticalMultiplier = if (verticalFlip) -1 else 1

		matrix.changeElement(n + 2  * verticalMultiplier, m + 26 * horizontalMultiplier, alive)
		matrix.changeElement(n + 3  * verticalMultiplier, m + 24 * horizontalMultiplier, alive)
		matrix.changeElement(n + 3  * verticalMultiplier, m + 26 * horizontalMultiplier, alive)
		matrix.changeElement(n + 4  * verticalMultiplier, m + 14 * horizontalMultiplier, alive)
		matrix.changeElement(n + 4  * verticalMultiplier, m + 15 * horizontalMultiplier, alive)
		matrix.changeElement(n + 4  * verticalMultiplier, m + 22 * horizontalMultiplier, alive)
		matrix.changeElement(n + 4  * verticalMultiplier, m + 23 * horizontalMultiplier, alive)
		matrix.changeElement(n + 4  * verticalMultiplier, m + 36 * horizontalMultiplier, alive)
		matrix.changeElement(n + 4  * verticalMultiplier, m + 37 * horizontalMultiplier, alive)
		matrix.changeElement(n + 5  * verticalMultiplier, m + 13 * horizontalMultiplier, alive)
		matrix.changeElement(n + 5  * verticalMultiplier, m + 17 * horizontalMultiplier, alive)
		matrix.changeElement(n + 5  * verticalMultiplier, m + 22 * horizontalMultiplier, alive)
		matrix.changeElement(n + 5  * verticalMultiplier, m + 23 * horizontalMultiplier, alive)
		matrix.changeElement(n + 5  * verticalMultiplier, m + 36 * horizontalMultiplier, alive)
		matrix.changeElement(n + 5  * verticalMultiplier, m + 37 * horizontalMultiplier, alive)
		matrix.changeElement(n + 6  * verticalMultiplier, m + 2  * horizontalMultiplier, alive)
		matrix.changeElement(n + 6  * verticalMultiplier, m + 3  * horizontalMultiplier, alive)
		matrix.changeElement(n + 6  * verticalMultiplier, m + 12 * horizontalMultiplier, alive)
		matrix.changeElement(n + 6  * verticalMultiplier, m + 18 * horizontalMultiplier, alive)
		matrix.changeElement(n + 6  * verticalMultiplier, m + 22 * horizontalMultiplier, alive)
		matrix.changeElement(n + 6  * verticalMultiplier, m + 23 * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 2  * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 3  * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 12 * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 16 * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 18 * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 19 * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 24 * horizontalMultiplier, alive)
		matrix.changeElement(n + 7  * verticalMultiplier, m + 26 * horizontalMultiplier, alive)
		matrix.changeElement(n + 8  * verticalMultiplier, m + 12 * horizontalMultiplier, alive)
		matrix.changeElement(n + 8  * verticalMultiplier, m + 18 * horizontalMultiplier, alive)
		matrix.changeElement(n + 8  * verticalMultiplier, m + 26 * horizontalMultiplier, alive)
		matrix.changeElement(n + 9  * verticalMultiplier, m + 13 * horizontalMultiplier, alive)
		matrix.changeElement(n + 9  * verticalMultiplier, m + 17 * horizontalMultiplier, alive)
		matrix.changeElement(n + 10 * verticalMultiplier, m + 14 * horizontalMultiplier, alive)
		matrix.changeElement(n + 10 * verticalMultiplier, m + 15 * horizontalMultiplier, alive)
	}

	fun simpleOscillator(n: Int, m: Int, alive: T, dead: T) {
		matrix.changeElement(n, m, alive)
		matrix.changeElement(n, m + 1, alive)
		matrix.changeElement(n, m + 2, alive)

	}

}

class RowBuilder<T>(
	private val dimension: Int
) {

	private var row: Row<T> = Row(dimension)

	fun build(): Row<T> {
		val product = row
		reset()
		return product
	}

	private fun reset() {
		row = Row(dimension)
	}

	fun all(element: T) {
		(0 until dimension).forEach { _ -> row.addElement(element) }
	}

}

fun <T> buildMatrix(rows: Int, columns: Int, lambda: MatrixBuilder<T>.() -> Unit): Matrix<T> {
	val builder = MatrixBuilder<T>(rows, columns)

	builder.lambda()

	return builder.build()
}

class Matrix<T>(
	val n: Int,
	val m: Int
) {

	private val rows: MutableList<Row<T>> = mutableListOf()

	fun getElement(row: Int, column: Int): T {
		return rows[row].getElement(column)
	}

	fun changeElement(row: Int, column: Int, new: T) {
		rows[row].changeElement(column, new)
	}

	fun addElement(element: T) {

	}

	fun addRow(row: Row<T>) {
		rows.add(row)
	}

	fun toList(): List<T> {
		val tempList = mutableListOf<T>()
		rows.forEach { row ->
			row.elements.forEach { tempList.add(it) }
		}
		return tempList
	}

	fun replaceAll(element: T) {
		val newRows = mutableListOf<Row<T>>()
		repeat((0 until n).count()) {
			newRows.add(Row(m))
		}
	}

}

class Row<T>(
	private val dimension: Int
) {

	val elements: MutableList<T> = mutableListOf()

	fun getElement(index: Int): T {
		return elements[index]
	}

	fun changeElement(index: Int, new: T) {
		elements[index] = new
	}

	fun addElement(element: T) {
		elements.add(element)
	}

	constructor(dimension: Int, element: T): this(dimension){
		elements.removeAll { true }
		repeat((0 until dimension).count()) { elements.add(element) }
	}

	constructor(dimension: Int, function: () -> T): this(dimension){
		elements.removeAll { true }
		repeat((0 until dimension).count()) { elements.add(function()) }
	}
}