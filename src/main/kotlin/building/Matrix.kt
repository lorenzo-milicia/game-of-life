package building

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
		TODO()
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