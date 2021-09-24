object Evolver {

	fun evolve(initialState: List<Int>, dimension: Int): List<Int> {

		val cells: MutableList<Cell> =
			initialState.map { if (it == 1) Cell(CellState.ALIVE) else Cell(CellState.DEAD) }.toMutableList()

		val cellsCopy = cells.toMutableList()

		for ((index, cell) in cellsCopy.withIndex()) {
			val column = index % dimension
			val row = index / dimension
			if (column == 0 || column == (dimension - 1) || row == 0 || row == (dimension - 1)) Unit
			else cell.evolve(
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

		return cellsCopy.map { if (it.isAlive) 1 else 0 }
	}

	fun evolve(initialState: Array<Int>, dimension: Int): Array<Int> {

		val cells: MutableList<Cell> =
			initialState.map { if (it == 1) Cell(CellState.ALIVE) else Cell(CellState.DEAD) }.toMutableList()

		val cellsCopy = cells.map { it.copy() }

		for ((index, cell) in cellsCopy.withIndex()) {
			val column = index % dimension
			val row = index / dimension
			if (column == 0 || column == dimension - 1 || row == 0 || row == dimension - 1) Unit
			else cell.evolve(
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

		return cellsCopy.map { if (it.isAlive) 1 else 0 }.toTypedArray()
	}
}