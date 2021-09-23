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
	val tempList: MutableList<Int> = initialState.toMutableList()
	var cell = tempList.removeAt(4)
	val neighbouringLivingCells = tempList.sum()
	cell = when {
		neighbouringLivingCells > 3 -> 0
		neighbouringLivingCells < 2 -> 0
		else                        -> 1
	}

	tempList.add(4, cell)
	return tempList

}