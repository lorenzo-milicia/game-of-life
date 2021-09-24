data class Cell(
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
			when {
				neighbouringLivingCells == MAGIC_NUMBER_FOR_RESUSCITATING -> resuscitate()
				else                                                      -> Unit
			}
		}
	}
}