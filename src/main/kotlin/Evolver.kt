object Evolver {

	fun evolve(initialState: List<Int>, dimension: Int): List<Int> {

		val cells: MutableList<Cell> =
			initialState.map { if (it == 1) Cell(CellState.ALIVE) else Cell(CellState.DEAD) }.toMutableList()

		for ((index, cell) in cells.withIndex()) {
			val column = index % dimension
			val row = index / dimension
			if (column == 0 || column == (dimension - 1) || row == 0 || row == (dimension - 1)) Unit
			else cell.decideFate(
				listOf(
					cells[dimension * (row - 1) + column - 1],
					cells[dimension * (row - 1) + column],
					cells[dimension * (row - 1) + column + 1],
					cells[dimension * (row) + column - 1],
					cells[dimension * (row) + column + 1],
					cells[dimension * (row + 1) + column - 1],
					cells[dimension * (row + 1) + column],
					cells[dimension * (row + 1) + column + 1],
				))
		}
		cells.forEach { it.executeFate() }
		return cells.map { if (it.isAlive) 1 else 0 }
	}
}