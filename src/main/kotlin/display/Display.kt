package display

import Main
import domain.Cell
import domain.PetriDish

object Display {

	var rotation = true

	fun display(petriDish: PetriDish) {
		Main.processing.run {
			for ((index, cell) in petriDish.cells.withIndex()) {
				val column = index % Main.grid.columns
				val row = index / Main.grid.columns
				push()
				translate(
					(column * Main.cellSize + Main.cellSize / 2).toFloat(),
					(row * Main.cellSize + Main.cellSize / 2).toFloat())
				if (rotation) {
					rotate(Main.t / 20f)
				}
				if (petriDish.isCellAlive(index)) {
					fill(((column + Main.t) % 255).toFloat(), 255f, 255f)

					cell.display()
				}
				pop()
			}
		}
	}

	private fun Cell.display() {
		Main.processing.run {
			//fill((rows + Main.t) % 255, 255, 255 * petriDish.getCells().get(i).getCellAliveCounter() / 50.f);
			square(
				(-Main.cellSize / 2).toFloat(),
				(-Main.cellSize / 2).toFloat(),
				Main.cellSize.toFloat())

		}
	}
}