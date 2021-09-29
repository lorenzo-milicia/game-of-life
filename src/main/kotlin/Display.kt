object Display {

	var rotation = true
	fun display(petriDish: PetriDish) {
		for (i in 0 until petriDish.numberOfCells) {
			val column = i % Main.grid.x
			val row = i / Main.grid.x
			if (petriDish.cells[i].isAlive) {
				//                Main.processing.fill((column + Main.t) % 255, 255, 255 * petriDish.getCells().get(i).getCellAliveCounter() / 50.f);
				Main.processing.fill(((column + Main.t) % 255).toFloat(), 255f, 255f)
				Main.processing.push()
				Main.processing.translate(
					(column * Main.cellSize + Main.cellSize / 2).toFloat(),
					(row * Main.cellSize + Main.cellSize / 2).toFloat())
				if (rotation) {
					Main.processing.rotate(Main.t / 20f)
				}
				Main.processing.square(
					(-Main.cellSize / 2).toFloat(),
					(-Main.cellSize / 2).toFloat(),
					Main.cellSize.toFloat())
				Main.processing.pop()
			}
		}
	}
}