package domain

import building.Matrix

class PetriDish private constructor(
	private val columns: Int,
	private val rows: Int,
	val cells: List<Cell>
) {

	init {
		if (columns * rows != cells.size) throw Exception("Mismatching dimensions and array size.")
	}

	val numberOfCells: Int
		get() = cells.size

	fun evolve() {
		for ((index, cell) in cells.withIndex()) {
			val column = index % columns
			val row = index / columns
			if (column == 0 || column == (columns - 1) || row == 0 || row == (rows - 1)) cell.kill()
			else {
				cell.decideFate(
					RelativeCoordinates.values().map {
						cells[columns * (row + it.offset.vertical) + column + it.offset.horizontal]
					}.count { it.isAlive }
				)
			}
		}

		cells.forEach { it.executeFate() }
	}

	fun switchStateOfCell(index: Int) = cells[index].switchState()

	fun isCellAlive(i: Int): Boolean = cells[i].isAlive

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