package domain

class Cell(
	private var state: CellState = CellState.DEAD
) {

	private var fate: CellState

	var cellAliveCounter = 0

	init {
		fate = state
	}

	val isAlive: Boolean
		get() = state == CellState.ALIVE

	val isDead: Boolean
		get() = state == CellState.DEAD

	fun kill() {
		fate = CellState.DEAD
	}

	fun resuscitate() {
		fate = CellState.ALIVE
		cellAliveCounter++
	}

	fun decideFate(neighbouringLivingCellsCount: Int) {
		val MAX_LIVING_NEIGHBOURING_CELLS = 3
		val MIN_LIVING_NEIGHBOURING_CELLS = 2
		val MAGIC_NUMBER_FOR_RESUSCITATING = 3

		when (state) {
			CellState.ALIVE -> when {
				neighbouringLivingCellsCount > MAX_LIVING_NEIGHBOURING_CELLS -> kill()
				neighbouringLivingCellsCount < MIN_LIVING_NEIGHBOURING_CELLS -> kill()
				else                                                         -> Unit
			}

			CellState.DEAD  -> when {
				neighbouringLivingCellsCount == MAGIC_NUMBER_FOR_RESUSCITATING -> resuscitate()
				//neighbouringLivingCells == 2 && Random.nextInt(100) == 1   -> resuscitate()
				else                                                           -> Unit
			}
		}
	}

	fun executeFate() {
		state = fate
	}

	fun switchState() {
		state = when (state) {
			CellState.ALIVE -> CellState.DEAD.also { kill() }
			CellState.DEAD  -> CellState.ALIVE.also { resuscitate() }
		}
	}
}