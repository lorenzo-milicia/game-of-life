import PetriBuilder.Companion.builder
import processing.core.PApplet

class Main: PApplet() {

	companion object {

		lateinit var processing: PApplet

//		const val cellSize = 10 //to avoid bugs make sure the resolution is a multiple of this
		var cellSize: Int = 10 //to avoid bugs make sure the resolution is a multiple of this
//		var resolution = Resolution(1000, 1000)
		lateinit var resolution: Resolution
		lateinit var grid: Grid
//		val grid = Grid(resolution.horizontal / cellSize, resolution.vertical / cellSize)

		var t = 0
	}


	lateinit var petriDish: PetriDish

	private var isEvolutionHappening = true

	override fun settings() {
		//fullScreen(JAVA2D); //remember to comment size() and change the resolution to that of the fullscreen window
		size(resolution.horizontal, resolution.vertical, JAVA2D)
	}

	override fun setup() {
		processing = this

		background(0)
		colorMode(HSB, 255f)

		val matrix =
			buildMatrix<Int>(grid.y, grid.x) {
				all(0)
				gospersCannon(20, 20, 1, 0)
				gospersCannon(80, 20, 1, 0, verticalFlip = true)
			}

		petriDish = builder {
			columns = grid.x
			rows = grid.y
			intList = matrix.toList()
		}
	}

	override fun draw() {
		background(0)

		Display.display(petriDish)

		if (isEvolutionHappening && frameCount % 2 == 0) {
			petriDish.evolve()
		}

		t++
	}

	override fun keyPressed() {
		if (key == ' ') {
			isEvolutionHappening = !isEvolutionHappening
		}
	}

	override fun mousePressed() {
		val column = mouseX / cellSize
		val row = mouseY / cellSize
		petriDish.switchStateOfCell(row * grid.x + column)
	}
}

fun main(args: Array<String>) {
	val horizontal = args[0].toInt()
	val vertical = args[1].toInt()
	val cellSize = args[2].toInt()

	Main.cellSize = cellSize
	Main.resolution = Resolution(horizontal, vertical)
	Main.grid = Grid(horizontal/cellSize, vertical/cellSize)
	PApplet.main("Main")
}