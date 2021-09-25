data class Cell(
	private var state: CellState = CellState.DEAD
) {

	private var fate: CellState

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
	}

	fun decideFate(neighbouringCells: List<Cell>) {
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

	fun executeFate() {
		state = fate
	}

	fun switchState() {
		state = if (isAlive) CellState.DEAD.also { kill() }
		else CellState.ALIVE.also { resuscitate() }
	}
}