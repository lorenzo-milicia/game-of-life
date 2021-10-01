import kotlin.random.Random

data class Cell(
	private var state: CellState = CellState.DEAD
) {

	private var fate: CellState

	var cellAliveCounter = 0;

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
		cellAliveCounter++;
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
				//neighbouringLivingCells == 2 && Random.nextInt(100) == 1   -> resuscitate()
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