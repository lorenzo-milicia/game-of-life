class RenderableCell(
	private val wrappedCell: Cell
): ICell {

	override var state: CellState = wrappedCell.state

	override var fate: CellState = wrappedCell.fate

	override val isAlive: Boolean
	get() = wrappedCell.isAlive

	override val isDead: Boolean
	get() = wrappedCell.isDead

	override fun kill() {
		wrappedCell.kill()
	}

	override fun resuscitate() {
		wrappedCell.resuscitate()
	}

	override fun decideFate(neighbouringCells: List<Cell>) {
		wrappedCell.decideFate(neighbouringCells)
	}

	override fun executeFate() {
		wrappedCell.executeFate()
	}

	override fun switchState() {
		wrappedCell.switchState()
	}


	val color: Color
		get() =
		if (isAlive) Color(255f,255f,255f,255f)
		else Color(0.0f,0.0f,0.0f,0.0f)

	var size: Float = 0.0f

	fun display() {
		Main.processing.run {
			push()
			fill(color.r,color.g,color.b,color.a)
			//fill((rows + Main.t) % 255, 255, 255 * petriDish.getCells().get(i).getCellAliveCounter() / 50.f);
			square(
				-size / 2,
				-size / 2,
				size
			)
			pop()

		}
	}

}