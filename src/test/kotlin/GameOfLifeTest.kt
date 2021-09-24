import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameOfLifeTest {

	@Test
	internal fun `a living cell surrounded by dead cells should die`() {
		val input: List<Int> = listOf(
			0, 0, 0,
			0, 1, 0,
			0, 0, 0
		)

		val output = evolve(input)

		assertEquals(
			listOf(
				0, 0, 0,
				0, 0, 0,
				0, 0, 0
			), output)
	}

	@Test
	internal fun `a living cell surrounded by two living cells should stay alive`() {
		val input: List<Int> = listOf(
			1, 1, 0,
			0, 1, 0,
			0, 0, 0
		)

		val output = evolve(input)

		assertEquals(
			listOf(
				1, 1, 0,
				0, 1, 0,
				0, 0, 0
			), output)
	}

	@Test
	internal fun `a living cell surrounded by more than three living cells should die`() {
		val input: List<Int> = listOf(
			1, 1, 1,
			0, 1, 1,
			0, 0, 0
		)

		val output = evolve(input)

		assertEquals(
			listOf(
				1, 1, 1,
				0, 0, 1,
				0, 0, 0
			), output)
	}

	@Test
	internal fun `a dead cell surrounded by three living cells should come back to life`() {
		val input: List<Int> = listOf(
			1, 1, 1,
			0, 0, 0,
			0, 0, 0
		)

		val output = evolve(input)

		assertEquals(
			listOf(
				1, 1, 1,
				0, 1, 0,
				0, 0, 0
			), output)
	}

	@Test
	internal fun `a 5 by 5 matrix of cells should evolve correctly`() {
		val input: List<Int> = listOf(
			0, 0, 0, 0, 0,
			0, 1, 1, 0, 0,
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0
		)

		val output = evolve(input)

		assertEquals(
			listOf(
				0, 0, 0, 0, 0,
				0, 1, 1, 0, 0,
				0, 1, 1, 0, 0,
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0
			), output)
	}
}

fun evolve(initialState: List<Int>): List<Int> {

	val cells: MutableList<Cell> =
		initialState.map { if (it == 1) Cell(CellState.ALIVE) else Cell(CellState.DEAD) }.toMutableList()

	for ((index, cell) in cells.withIndex()) {
		val column = index % 5
		val row = index / 5
		if (column == 0 || column == 4 || row == 0 || row == 4) Unit
		else cell.evolve(listOf(
			cells[5 * (row-1) + column - 1],
			cells[5 * (row-1) + column    ],
			cells[5 * (row-1) + column + 1],
			cells[5 * (row  ) + column - 1],
			cells[5 * (row  ) + column + 1],
			cells[5 * (row+1) + column - 1],
			cells[5 * (row+1) + column    ],
			cells[5 * (row+1) + column + 1],
		))
	}

	return cells.map { if (it.isAlive) 1 else 0 }
}

