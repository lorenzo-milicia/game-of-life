interface ICell {
	var state: CellState
	var fate: CellState
	val isAlive: Boolean
	val isDead: Boolean

	fun kill()
	fun resuscitate()
	fun decideFate(neighbouringCells: List<Cell>)
	fun executeFate()
	fun switchState()
}

data class Cell(
	override var state: CellState = CellState.DEAD
): ICell {

	override lateinit var fate: CellState

	var cellAliveCounter = 0

	init {
		fate = state
	}

	override val isAlive: Boolean
		get() = state == CellState.ALIVE

	override val isDead: Boolean
		get() = state == CellState.DEAD

	override fun kill() {
		fate = CellState.DEAD
	}

	override fun resuscitate() {
		fate = CellState.ALIVE
		cellAliveCounter++
	}

	override fun decideFate(neighbouringCells: List<Cell>) {
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
				//neighbouringLivingCells == 2 && Random.nextInt(100) == 1   -> resuscitate()
				else                                                      -> Unit
			}
		}
	}

	override fun executeFate() {
		state = fate
	}

	override fun switchState() {
		state = if (isAlive) CellState.DEAD.also { kill() }
		else CellState.ALIVE.also { resuscitate() }
	}
}