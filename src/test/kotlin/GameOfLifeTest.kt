import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameOfLifeTest {

	@Test
	internal fun `a living cell surrounded by dead cells should die`() {
		val input: List<Int> = listOf(
			0,0,0,
			0,1,0,
			0,0,0
		)

		val output = evolve(input)

		assertEquals(listOf(
			0,0,0,
			0,0,0,
			0,0,0
		), output)
	}

	@Test
	internal fun `a living cell surrounded by two living cells should stay alive`() {
		val input: List<Int> = listOf(
			1,1,0,
			0,1,0,
			0,0,0
		)

		val output = evolve(input)

		assertEquals(listOf(
			1,1,0,
			0,1,0,
			0,0,0
		), output)
	}
}

fun evolve(initialState: List<Int>): List<Int> {
	val cells: MutableList<Cell> = initialState.map { if (it == 0) Cell(CellState.DEAD) else Cell(CellState.ALIVE) } as MutableList<Cell>

	var cell: Cell = cells.removeAt(4)
	val neighbouringLivingCells = cells.count { it.isAlive }
	cell = when {
		neighbouringLivingCells > 3 -> Cell(CellState.ALIVE)
		neighbouringLivingCells < 2 -> Cell()
		else                        -> Cell(CellState.ALIVE)
	}


	cells.add(4, cell)
	return cells.map { if(it.isAlive) 1 else 0 }
}

class Cell(
	var state: CellState = CellState.DEAD
) {
	val isAlive: Boolean
	get() = state == CellState.ALIVE

	val isDead: Boolean
		get() = state == CellState.DEAD

}



enum class CellState {
	ALIVE,
	DEAD
}