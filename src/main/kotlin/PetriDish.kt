class PetriDish(
	private val columns: Int,
	private val rows: Int
) {
	//TODO: Posso mettere val e costruttore secondario insieme?
	var cells: List<Cell> = Array(columns * rows) { Cell() }.toList()

	val numberOfCells: Int
		get() = cells.size

	fun evolve() {
		for ((index, cell) in cells.withIndex()) {
			val column = index % columns
			val row = index / columns
			if (column == 0 || column == (columns - 1) || row == 0 || row == (rows - 1)) cell.kill()
			else cell.decideFate(
				listOf(
					cells[columns * (row - 1) + column - 1],
					cells[columns * (row - 1) + column],
					cells[columns * (row - 1) + column + 1],
					cells[columns * (row) + column - 1],
					cells[columns * (row) + column + 1],
					cells[columns * (row + 1) + column - 1],
					cells[columns * (row + 1) + column],
					cells[columns * (row + 1) + column + 1],
				))
		}
		cells.forEach { it.executeFate() }
	}

	fun switchStateOfCell(index: Int) {
		cells[index].switchState()
	}

	constructor(columns: Int, rows: Int, cells: List<Cell>): this(columns, rows) {
		this.cells = cells
	}
}