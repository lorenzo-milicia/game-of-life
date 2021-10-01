object Display {

	var rotation = true

	fun display(petriDish: PetriDish) {
		for ((index, cell) in petriDish.cells.withIndex()) {
			val column = index % Main.grid.x
			val row = index / Main.grid.x
			cell.display(column, row)
		}
	}

	private fun Cell.display(columnReference: Int, rowReference: Int) {
		Main.processing.run {
			if (isAlive) {
				//fill((column + Main.t) % 255, 255, 255 * petriDish.getCells().get(i).getCellAliveCounter() / 50.f);
				fill(((columnReference + Main.t) % 255).toFloat(), 255f, 255f)
				push()
				translate(
					(columnReference * Main.cellSize + Main.cellSize / 2).toFloat(),
					(rowReference * Main.cellSize + Main.cellSize / 2).toFloat())
				if (rotation) {
					rotate(Main.t / 20f)
				}
				square(
					(-Main.cellSize / 2).toFloat(),
					(-Main.cellSize / 2).toFloat(),
					Main.cellSize.toFloat())
				pop()
			}
		}
	}
}