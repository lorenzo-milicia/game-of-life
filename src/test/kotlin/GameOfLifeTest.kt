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
}

fun evolve(initialState: List<Int>): List<Int> {
	return listOf(
		0,0,0,
		0,0,0,
		0,0,0
	)
}