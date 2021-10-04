interface IPetriDish {
	val columns: Int
	val rows: Int
	val cells: List<ICell>
	val numberOfCells: Int

	fun evolve()
	fun switchStateOfCell(index: Int)
	fun isCellAlive(index: Int): Boolean

	companion object
}

class PetriDish private constructor(
	override val columns: Int,
	override val rows: Int,
	override val cells: List<Cell>
): IPetriDish {

	override val numberOfCells: Int
		get() = cells.size

	override fun evolve() {
		for ((index, cell) in cells.withIndex()) {
			val column = index % columns
			val row = index / columns
			if (column == 0 || column == (columns - 1) || row == 0 || row == (rows - 1)) cell.kill()
			else cell.decideFate(
				listOf(
					cells[columns * (row - 1) + column - 1],
					cells[columns * (row - 1) + column + 0],
					cells[columns * (row - 1) + column + 1],
					cells[columns * (row + 0) + column - 1],
					cells[columns * (row + 0) + column + 1],
					cells[columns * (row + 1) + column - 1],
					cells[columns * (row + 1) + column + 0],
					cells[columns * (row + 1) + column + 1],
				))
		}

		cells.forEach { it.executeFate() }
	}

	override fun switchStateOfCell(index: Int) {
		cells[index].switchState()
	}

	override fun isCellAlive(i: Int): Boolean = cells[i].isAlive

	companion object Factory {

		@JvmName("ofinteger")
		fun of(columns: Int, rows: Int, integerList: List<Int>) = PetriDish(
			columns,
			rows,
			integerList.map {
				if (it == 0) Cell(CellState.DEAD) else Cell(CellState.ALIVE)
			}
		)
		@JvmName("ofboolean")
		fun of(columns: Int, rows: Int, booleanList: List<Boolean>) = PetriDish(
			columns,
			rows,
			booleanList.map {
				if (it) Cell(CellState.ALIVE) else Cell()
			}
		)
		@JvmName("ofcells")
		fun of(columns: Int, rows: Int, cellList: List<Cell>) = PetriDish(
			columns,
			rows,
			cellList
		)
		@JvmName("ofmatrix")
		fun of(matrix: Matrix<Int>) = PetriDish(
			matrix.m,
			matrix.n,
			matrix.toList().map {
				if (it == 0) Cell(CellState.DEAD) else Cell(CellState.ALIVE)
			}
		)
	}
}