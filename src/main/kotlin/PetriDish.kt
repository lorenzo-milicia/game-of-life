class PetriDish(
	cells: List<Int>,
	private val columns: Int,
	private val rows: Int
) {

	val cells: MutableList<Cell>

	val numberOfCells: Int
		get() = cells.size

	init {
		if (columns * rows != cells.size) throw Exception("Mismatching number of cells and dimensions.")
		this.cells = cells.map { if (it == 1) Cell(CellState.ALIVE) else Cell(CellState.DEAD) }.toMutableList()
	}

	fun evolve(): List<Int> {
		for ((index, cell) in cells.withIndex()) {
			val column = index % columns
			val row = index / columns
			if (column == 0 || column == (columns - 1) || row == 0 || row == (rows - 1)) cell.kill()
			else cell.decideFate(
				listOf(
					cells[columns * (row - 1) + column - 1],
					cells[columns * (row - 1) + column    ],
					cells[columns * (row - 1) + column + 1],
					cells[columns * (row    ) + column - 1],
					cells[columns * (row    ) + column + 1],
					cells[columns * (row + 1) + column - 1],
					cells[columns * (row + 1) + column    ],
					cells[columns * (row + 1) + column + 1],
				))
		}
		cells.forEach { it.executeFate() }
		return cells.map { if (it.isAlive) 1 else 0 }
	}

	fun switchStateOfCell(index: Int) {
		cells[index].switchState()
	}
}