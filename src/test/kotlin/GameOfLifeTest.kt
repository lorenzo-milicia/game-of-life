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

		val output = Evolver.evolve(input)

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

		val output = Evolver.evolve(input)

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

		val output = Evolver.evolve(input)

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

		val output = Evolver.evolve(input)

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

		val output = Evolver.evolve(input)

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



