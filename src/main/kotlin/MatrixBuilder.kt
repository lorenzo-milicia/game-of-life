class MatrixBuilder<T>(
	private val rows: Int,
	private val columns: Int
) {

	private val matrix: Matrix<T> = Matrix<T>(rows, columns)

	fun row(rowFun: RowBuilder<T>.() -> Unit) {
		val rowBuilder = RowBuilder<T>(columns)

		rowFun(rowBuilder)

		matrix.addRow(rowBuilder.build())
	}

	fun all(element: T) {
		(0 until rows).forEach { matrix.addRow(Row<T>(columns, element)) }
	}

	fun build(): Matrix<T> {
		return matrix
	}

	fun spaceship(n: Int, m: Int, alive: T, dead: T) {
		matrix.changeElement(n + 0, m + 0, dead)
		matrix.changeElement(n + 1, m + 0, dead)
		matrix.changeElement(n + 2, m + 0, alive)
		matrix.changeElement(n + 0, m + 1, alive)
		matrix.changeElement(n + 1, m + 1, dead)
		matrix.changeElement(n + 2, m + 1, alive)
		matrix.changeElement(n + 0, m + 2, dead)
		matrix.changeElement(n + 1, m + 2, alive)
		matrix.changeElement(n + 2, m + 2, alive)
	}

	fun gospersCannon(n: Int, m: Int, alive: T, dead: T) {
		matrix.changeElement(n + 2 , m + 26, alive)
		matrix.changeElement(n + 3 , m + 24, alive)
		matrix.changeElement(n + 3 , m + 26, alive)
		matrix.changeElement(n + 4 , m + 14, alive)
		matrix.changeElement(n + 4 , m + 15, alive)
		matrix.changeElement(n + 4 , m + 22, alive)

		matrix.changeElement(n + 4 , m + 23, alive)
		matrix.changeElement(n + 4 , m + 36, alive)
		matrix.changeElement(n + 4 , m + 37, alive)
		matrix.changeElement(n + 5 , m + 13, alive)
		matrix.changeElement(n + 5 , m + 17, alive)
		matrix.changeElement(n + 5 , m + 22, alive)

		matrix.changeElement(n + 5 , m + 23, alive)
		matrix.changeElement(n + 5 , m + 36, alive)
		matrix.changeElement(n + 5 , m + 37, alive)
		matrix.changeElement(n + 6 , m + 2 , alive)
		matrix.changeElement(n + 6 , m + 3 , alive)
		matrix.changeElement(n + 6 , m + 12, alive)

		matrix.changeElement(n + 6 , m + 18, alive)
		matrix.changeElement(n + 6 , m + 22, alive)
		matrix.changeElement(n + 6 , m + 23, alive)
		matrix.changeElement(n + 7 , m + 2 , alive)
		matrix.changeElement(n + 7 , m + 3 , alive)
		matrix.changeElement(n + 7 , m + 12, alive)

		matrix.changeElement(n + 7 , m + 16, alive)
		matrix.changeElement(n + 7 , m + 18, alive)
		matrix.changeElement(n + 7 , m + 19, alive)
		matrix.changeElement(n + 7 , m + 24, alive)
		matrix.changeElement(n + 7 , m + 26, alive)
		matrix.changeElement(n + 8 , m + 12, alive)

		matrix.changeElement(n + 8 , m + 18, alive)
		matrix.changeElement(n + 8 , m + 26, alive)
		matrix.changeElement(n + 9 , m + 13, alive)
		matrix.changeElement(n + 9 , m + 17, alive)
		matrix.changeElement(n + 10, m + 14, alive)
		matrix.changeElement(n + 10, m + 15, alive)

	}

}

class RowBuilder<T>(
	private val dimension: Int
) {

	private val row: Row<T> = Row(dimension)

	fun all(element: T) {
		(0 until dimension).forEach { _ -> row.addElement(element) }
	}

	fun build(): Row<T> {
		return row
	}
}

fun <T> buildMatrix(rows: Int, columns: Int, lambda: MatrixBuilder<T>.() -> Unit): Matrix<T> {
	val builder = MatrixBuilder<T>(rows, columns)

	builder.lambda()

	return builder.build()
}

class Matrix<T>(
	private val n: Int,
	private val m: Int
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
		(0 until n).forEach {
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
		(0 until dimension).forEach { elements.add(element) }
	}
}