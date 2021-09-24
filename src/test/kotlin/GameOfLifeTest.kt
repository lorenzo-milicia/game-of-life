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
		initialState.map { if (it == 0) Cell(CellState.DEAD) else Cell(CellState.ALIVE) } as MutableList<Cell>

	var cell = cells.removeAt(4)
	cell.evolve(cells)

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

	fun evolve(neighbouringCells: List<Cell>) {
		val neighbouringLivingCells = neighbouringCells.count { it.isAlive }
		val MAX_LIVING_NEIGHBOURING_CELLS = 3
		val MIN_LIVING_NEIGHBOURING_CELLS = 2
		val MAGIC_NUMBER_FOR_RESUSCITATING = 3

		if (isAlive) {
			when {
				neighbouringLivingCells > MAX_LIVING_NEIGHBOURING_CELLS -> kill()
				neighbouringLivingCells < MIN_LIVING_NEIGHBOURING_CELLS -> kill()
				else                                                    -> Unit
			}
		}
		else {
			when (neighbouringLivingCells) {
				MAGIC_NUMBER_FOR_RESUSCITATING -> resuscitate()
				else                           -> Unit
			}
		}
	}
}

enum class CellState {
	ALIVE,
	DEAD
}