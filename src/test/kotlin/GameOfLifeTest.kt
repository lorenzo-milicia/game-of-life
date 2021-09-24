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
}

fun evolve(initialState: List<Int>): List<Int> {
	val cells: MutableList<Cell> =
		initialState.map { if (it == 0) Cell(CellState.DEAD) else Cell(CellState.ALIVE) } as MutableList<Cell>

	val cell: Cell = cells.removeAt(4)
	val neighbouringLivingCells = cells.count { it.isAlive }

	if (cell.isAlive) {
		when {
			neighbouringLivingCells > 3 -> cell.kill()
			neighbouringLivingCells < 2 -> cell.kill()
			else                        -> Unit
		}
	}
	else {
		when (neighbouringLivingCells) {
			3    -> cell.resuscitate()
			else -> Unit
		}
	}

	cells.add(4, cell)
	return cells.map { if (it.isAlive) 1 else 0 }
}

class Cell(
	private var state: CellState = CellState.DEAD
) {

	val isAlive: Boolean
		get() = state == CellState.ALIVE

	val isDead: Boolean
		get() = state == CellState.DEAD

	fun kill() {
		state = CellState.DEAD
	}

	fun resuscitate() {
		state = CellState.ALIVE
	}
}


enum class CellState {
	ALIVE,
	DEAD
}